<!--m?c header cùng v?i th? navbar-->
<header class="header">
    <div class="header__container">
        <div class="header__container__logo">
            <img src="${pageContext.request.contextPath}/resources/images/logo/logo1.png" alt="logo">
        </div>
        <nav class="header__container__navbar">
            <div class="header__container_navbar__head">
                <div class="header__container__logo">
                    <img src="${pageContext.request.contextPath}/resources/images/logo/logo1.png" alt="logo">
                </div>
                <button class="close-menu-btn" type="button"></button>
            </div>
            <ul>
                <!--ph?n dropdown menu con tr? xu?ng khi hover tru?t vào-->
                <li class="dropdown">
                    <a href="#">Products</a>
                    <ul class="sub-menu">
                        <li>
                            <a href=""><span>Camera Security</span></a>
                        </li>
                        <li>
                            <a href=""><span>Camera Links</span></a>
                        </li>
                        <li>
                            <a href=""><span>Lights</span></a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#">About Us</a>
                    <ul class="sub-menu">
                        <li>
                            <a href=""><span>About</span></a>
                        </li>
                        <li>
                            <a href=""><span>Contact</span></a>
                        </li>
                    </ul>
                </li>
                <li><a href="#">Protect</a></li>
                <li class="dropdown">
                    <a href="#">Support</a>
                    <ul class="sub-menu">
                        <li>
                            <a href=""><span>FAQ</span></a>
                        </li>
                        <li>
                            <a href=""><span>H??ng d?n s? d?ng</span></a>
                        </li>
                        <li>
                            <a href=""><span>Chính sách b?o hành</span></a>
                        </li>
                        <li>
                            <a href=""><span>Tr?m b?o hành</span></a>
                        </li>
                    </ul>
                </li>
            </ul>

        </nav>
        <div class="header__container__right">
            <!--ch?c n?ng search -->
            <div class="search-container">
                <button class="search-btn icon-btn" type="button">
                    <i class="fa-solid fa-search"></i>
                </button>   
                <!--muc form chua thanh search-->
                <div class="search-container__form">
                    <!--noi dung search btn khi hoover vào-->                    
                    <form class="flex my-7 place-items-center max-w-lg mx-auto">   
                        <label for="voice-search" class="sr-only">Search</label>
                        <div class="relative w-full">
                            <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
                                <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 21 21">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11.15 5.6h.01m3.337 1.913h.01m-6.979 0h.01M5.541 11h.01M15 15h2.706a1.957 1.957 0 0 0 1.883-1.325A9 9 0 1 0 2.043 11.89 9.1 9.1 0 0 0 7.2 19.1a8.62 8.62 0 0 0 3.769.9A2.013 2.013 0 0 0 13 18v-.857A2.034 2.034 0 0 1 15 15Z"/>
                                </svg>
                            </div>
                            <input type="text" id="voice-search" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Search Mockups, Logos, Design Templates..." required />
                            <button type="button" class="absolute inset-y-0 end-0 flex items-center pe-3">
                                <svg class="w-4 h-4 text-gray-500 dark:text-gray-400 hover:text-gray-900 dark:hover:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 16 20">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 7v3a5.006 5.006 0 0 1-5 5H6a5.006 5.006 0 0 1-5-5V7m7 9v3m-3 0h6M7 1h2a3 3 0 0 1 3 3v5a3 3 0 0 1-3 3H7a3 3 0 0 1-3-3V4a3 3 0 0 1 3-3Z"/>
                                </svg>
                            </button>
                        </div>
                        <button type="submit" class="inline-flex items-center py-2.5 px-3 ms-2 text-sm font-medium text-white bg-blue-700 rounded-lg border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                            <svg class="w-4 h-4 me-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                            </svg>Search
                        </button>
                    </form>
                </div>

            </div>
            <button class="notice-btn icon-btn"><i class="fa-regular fa-bell"></i></button>
            <button class="cart-btn icon-btn"><i class="fa-solid fa-cart-arrow-down"></i></button>
            <button class="icon-btn button-signup" type="button"><span class="signup-in">sign up</span> &ensp; |</button>
            <button class="icon-btn button-signin" type="button"><span class="signup-in">sign in</span></button>
            <button class="open-menu-btn">
                <span class="line line-1"></span>
                <span class="line line-2"></span>
                <span class="line line-3"></span>
            </button>
        </div>
</header>

<!--t?o form m?u login / sign up | s? d?ng ki?n th?c hi?n th? ki?u base model-->
 <div class="modal">
     <!--t?o l?p ph? khi mà base model ?c m? trang homepage s? b? m? ?i-->
     <div class="modal__overlay"></div>
     <div class="modal__body modal__body-sub">
         <!--ch? này s? d?ng forrm ??n ký ??ng ký ??ng nh?p.-->
         <!--register form-->
         <div class="auth-form  auth-form__register auth-form--display">
             <div class="auth-form__container">
                 <div class="auth-form__header">
                     <h3 class="auth-form__heading">REGISTER</h3>
                     <span class="auth-form__swtich-btn">SIGN IN</span>
                 </div>

                 <div class="auth-form__form">
                     <div class="auth-form__group">
                         <input type="text" class="auth-form__input" placeholder="Your Email">
                     </div>
                     <div class="auth-form__group">
                         <input type="password" class="auth-form__input" placeholder="Your Passowrd">
                     </div>
                     <div class="auth-form__group">
                         <input type="password" class="auth-form__input" placeholder="Enter Password again!">
                     </div>
                 </div>

                 <div class="auth-form__controls">
                     <button class="btn btn--back">come back</button>
                     <button class="btn btn--primary">Signup</button>
                 </div>
             </div>

             <div class="auth-form__socials">
                 <a href="" class=" auth-form__socials-facebook btn btn--size-s btn--with-icon">
                     <i class=" auth-form__socials-icon fa-brands fa-square-facebook"></i>
                    <span class="auth-form__socials-label">Connect with Facebook</span>
                 </a>
                 <a href="" class="auth-form__socials-google btn btn--size-s btn--with-icon">
                     <i class="auth-form__socials-icon fa-brands fa-google"></i>
                     <span class="auth-form__socials-label">Connect with Google mail</span>
                 </a>
             </div>

         </div> 

         <!--login form-->
         <div class="auth-form auth-form__login">
             <div class="auth-form__container">
                 <div class="auth-form__header">
                     <h3 class="auth-form__heading">SIGN IN</h3>
                     <span class="auth-form__swtich-btn">REGISTER</span>
                 </div>

                 <div class="auth-form__form">
                     <div class="auth-form__group">
                         <input type="text" class="auth-form__input" placeholder="Your Email">
                     </div>
                     <div class="auth-form__group">
                         <input type="password" class="auth-form__input" placeholder=" Your Passowrd ">
                     </div>
                 </div>

                 <div class="auth-form__aside">
                     <div class="auth-form__help">
                         <a href="" class="auth-form__link  auth-form__link-forgot ">Forgot password</a>
                         <span class="auth-form__help-separate"></span>
                         <a href="" class="auth-form__link">for help?</a>
                     </div>
                 </div>

                 <div class="auth-form__controls">
                     <button class="btn btn--back">come back</button>
                     <button class="btn btn--primary">Sign in</button>
                 </div>
             </div>

             <div class="auth-form__socials">
                 <a href="" class=" auth-form__socials-facebook btn btn--size-s btn--with-icon">
                     <i class=" auth-form__socials-icon fa-brands fa-square-facebook"></i>
                    <span class="auth-form__socials-label"> connect with Facebook</span>
                 </a>
                 <a href="" class="auth-form__socials-google btn btn--size-s btn--with-icon">
                     <i class="auth-form__socials-icon fa-brands fa-google"></i>
                     <span class="auth-form__socials-label"> connect with Google mail</span>
                 </a>
             </div>

         </div> 
     </div>
 </div>
