$(document).ready(function () {
    $("#admin_tab a").click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    createTableofUsers();

    function createTableofUsers() {
        var table = document.getElementById("alluser_table").getElementsByTagName('tbody')[0];

        $.getJSON("admin_page/users", function (data) {
            $.each(data, function (index, user) {
                var row = table.insertRow(table.rows.length)
                var id = row.insertCell(0)
                var firstName = row.insertCell(1)
                var lastName = row.insertCell(2)
                var age = row.insertCell(3)
                var email = row.insertCell(4)
                var ROLE = row.insertCell(5)
                var edit = row.insertCell(6)
                var del = row.insertCell(7)
                id.innerHTML = user.id
                firstName.innerHTML = user.firstName
                lastName.innerHTML = user.lastName
                age.innerHTML = user.age
                email.innerHTML = user.username
                ROLE.innerHTML = user.roles
                edit.innerHTML = ('<button type="button" class="btn btn-info btn-sm" ' +
                    'data-toggle="modal"\n' +
                    'id="button_modal_edit_userid=' + user.id + '"' +
                    'data-target="#modal_edit_user">Ред-ть\n' +
                    '</button>')
                del.innerHTML = ('<button type="button" class="btn btn-danger btn-sm" ' +
                    'data-toggle="modal"\n' +
                    'id="button_modal_delete_userid=' + user.id + '"' +
                    'data-target="#delete_user">Удалить\n' +
                    '</button>')

                document.getElementById('button_modal_edit_userid=' + user.id).addEventListener('click', function () {
                    edit_button_function(user.id)
                })

                document.getElementById('button_modal_delete_userid=' + user.id).addEventListener('click', function () {
                    delete_button_function(user.id)
                })


            });
        })
    }

    function edit_button_function(id) {
        $.getJSON(('admin_page/users/' + id), function (user) {
            $('#edit_inputID').val(user.id);
            $('#edit_inputFirstName').val(user.firstName);
            $('#edit_inputLastName').val(user.lastName);
            $('#edit_inputAge').val(user.age);
            $('#edit_inputUsername').val(user.username);
            $('#edit_inputPassword').val(user.password);
            if (hasOption('edit_inputRoles')){
                $('#edit_inputRoles').find('option').remove();
            }
            $.getJSON("admin_page/roles", function (data) {
                $.each(data, function (index, role) {
                    $('#edit_inputRoles').append($('<option>', {
                        value: role,
                        text: role,
                        name: "role"
                    }));
                })
            })
        })
    }

    $('#accept_user_edit_button').click(function () {
        var data = $('#form_modal_edit_user').serializeObject()
        var roles = []
        if (toString.call(data.roles) !== toString.call(roles)) {
            roles.unshift(data.roles)
            data.roles = roles
            sendNewUserForm(data)
        } else {
            sendNewUserForm(data)
        }
    })

    function hasOption(id){
        var select = document.getElementById(id),
            options = select.options;
        return options.length > 0;
    }

    function delete_button_function(id) {
        var userID= id
        $.getJSON(('admin_page/users/' + id), function (user) {
            $('#delete_inputID').val(user.id);
            $('#delete_inputFirstName').val(user.firstName);
            $('#delete_inputLastName').val(user.lastName);
            $('#delete_inputAge').val(user.age);
            $('#delete_inputUsername').val(user.username);
            $('#delete_inputPassword').val(user.password);
            if (hasOption('delete_inputRoles')){
                $('#delete_inputRoles').find('option').remove();
            }
            $.each(user.roles, function (index, role) {
                $('#delete_inputRoles').append($('<option>', {
                    value: role,
                    text: role,
                    name: 'role'
                }));
            })
        })
        $('#delete_modal_button').click (function (){
            console.log("DELETING ID = "+ userID)
            $.ajax({
                url: ('admin_page/users/' + userID),
                type: 'DELETE',
                success: [
                    function () {
                    console.log("SUCESS")
                        $('#alluser_table tbody').empty();
                        createTableofUsers()
                        $('#admin_tab a[href="#user_table"]').tab('show')
                    }]
            });
        })
    }

    $('#admin_tab a').on('shown.bs.tab', function (e) {
        var current_tab = $(e.target).attr("href")
        if (current_tab === "#add_user") {
            $("#add_form", function () {
                $.getJSON("admin_page/roles", function (data) {
                    $.each(data, function (index, role) {
                        $('#roles').append($('<option>', {
                            value: role,
                            text: role
                        }));
                    })

                })
            })
        }
    });

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };


    $('#send_form_button').click(function () {
        var data = $('#add_form').serializeObject()
        var roles = []
        if (toString.call(data.roles) !== toString.call(roles)) {
            roles.unshift(data.roles)
            data.roles = roles
            sendNewUserForm(data)
        } else {
            sendNewUserForm(data)
        }
    })

    function sendNewUserForm(data) {
        $.ajax({
            url: 'admin_page/users',
            type: "POST",
            data: (JSON.stringify(data)),
            contentType: "application/json",
            success: [
                function () {
                    $('#alluser_table tbody').empty();
                    createTableofUsers()
                    $('#admin_tab a[href="#user_table"]').tab('show')

                }]

        })
    }
});




