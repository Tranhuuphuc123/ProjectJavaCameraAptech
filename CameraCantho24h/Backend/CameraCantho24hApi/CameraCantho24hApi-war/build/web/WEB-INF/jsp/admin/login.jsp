<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!--nạp giao diện chính của những layout cố định mà mình tách riêng ở mục
tags/layouts  (tránh viết đi viết lại như bên vscode làm giao diện thủ công)-->
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts/" %>

<!--gọi giao diện layout chính cố định ra -->
<layout:nolayout>
    <jsp:attribute name="title">
        Login
    </jsp:attribute>
        
    <jsp:attribute name="styles">
    </jsp:attribute>
       
    <jsp:attribute name="scripts">
        <script>
            Validator({
                form: '#sign-in-form',
                formGroup: '.login-form-gr',
                errorSelector: '.form-message',
                rules: [
                    Validator.isRequired('#email', 'Email is required'),
                    Validator.isEmail('#email', 'Email not correct format'),
                    Validator.isRequired('#password', 'Password is required'),
                    Validator.minLength('#password', 8, 'Enter at least 8 characters'),

                    // Validate for front-end, Java validate backend
                ],
                onSubmit: function(data) {
                    const loginPath = window.APP_NAME + '/api/users/login';
                    fetch(loginPath, {
                        method: 'POST',
                        credentials: 'same-origin',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({
                            email: data.email,
                            password: data.password,
                        })
                    })
                    .then(res => res.json())
                    .then(res => {
                        if (res.code == 200) {
                            location.href = window.APP_NAME + '/admin'
                        } else if (res.code == 400) {
                            const errElm = document.getElementById('error')
                            document.getElementById('error-content').textContent = res.message;
                            errElm.classList.remove('hidden')
                            errElm.classList.add('flex')
                        }
                    })
                }
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <section class="bg-gray-50 dark:bg-gray-900">
            <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
                <a href="#" class="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white">
                    <img class="w-8 h-8 mr-2" src="${pageContext.request.contextPath}/resources/images/logo/logo1.png" alt="logo">
                    CameraCantho24h    
                </a>
                <div class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
                    <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                        <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                            Sign in to your account
                        </h1>
                        <form class="space-y-4 md:space-y-6" id="sign-in-form">
                            <div class="login-form-gr">
                                <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                                <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="name@company.com">
                                <span class="text-base font-normal text-red-500 mt-1 form-message"></span>
                            </div>
                            <div class="login-form-gr">
                                <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                                <input type="password" name="password" id="password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                <span class="text-base font-normal text-red-500 mt-1 form-message"></span>
                            </div>
                            <div class="flex items-center justify-between">
                                <a href="#" class="text-sm font-medium text-blue-700 hover:underline dark:text-gray-300">Forgot password?</a>
                            </div>
                            <button type="submit" class="w-full text-white bg-blue-600 hover:bg-blue-700 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                                Sign in
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </jsp:body>
</layout:nolayout>