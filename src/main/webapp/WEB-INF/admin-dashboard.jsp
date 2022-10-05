<%--
  Created by IntelliJ IDEA.
  User: lucademenego
  Date: 02/10/22
  Time: 11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin</title>
    <%@include file="common.jsp" %>
</head>
<body>
<div class="flex h-screen w-screen items-center justify-center bg-gradient-to-br from-blue-400 via-blue-200 to-blue-400">
    <div class="flex h-[70vh] w-[80vw] flex-col rounded-md bg-white drop-shadow-md items-start lg:w-[50vw] md:h-[60vh]">
        <%@include file="header.jsp" %>
        <div class="flex flex-col gap-16 w-full h-full min-h-0 p-10 overflow-auto md:flex-row md:gap-0">
            <div class="flex-1 flex flex-col">
                <div class="flex flex-row items-center md:pl-4 gap-3 tracking-wider">
                    <svg width="30" height="30" viewBox="0 0 42 42" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <g clip-path="url(#clip0_2_31)">
                            <path d="M21 0C9.40201 0 0 9.40201 0 21C0 32.598 9.40201 42 21 42C32.598 42 42 32.598 42 21C42 9.40201 32.598 0 21 0ZM20.9574 11.6769C22.8944 11.7024 24.4988 13.2792 25.0319 15.2886C25.2351 16.1084 25.2581 16.9401 25.0532 17.8085C24.7442 19.031 24.1257 20.2252 22.9734 20.9588L24.6529 21.7566L28.6436 23.6888C29.0356 23.8568 29.2314 24.1928 29.2314 24.6968V28.5598C29.2314 29.3565 29.2947 30.0436 28.3072 30.3245H13.6077C12.658 30.3108 12.6187 29.3489 12.641 28.5598V24.6968C12.641 24.1928 12.8513 23.8423 13.2713 23.6462L17.3869 21.7566L18.984 21C18.256 20.524 17.682 19.8666 17.262 19.0266C16.7576 17.9177 16.5577 16.4781 16.9255 15.1622C17.3906 13.3785 18.8472 11.776 20.9574 11.6769ZM14.1117 13.4402C14.7277 13.4402 15.2872 13.6227 15.7912 13.9867C15.6514 14.3786 15.5331 14.792 15.4348 15.2261C15.3368 15.66 15.2872 16.1143 15.2872 16.5904C15.2872 17.2904 15.3851 17.9624 15.5811 18.6064C15.777 19.2504 16.0574 19.839 16.4215 20.371C16.2535 20.595 16.03 20.7908 15.7499 20.9588L12.6409 22.3869C12.2209 22.6109 11.8849 22.9251 11.6329 23.3311C11.3809 23.7371 11.2553 24.1928 11.2553 24.6968V28.5599H8.19011C7.74543 28.5529 7.4451 28.1048 7.43344 27.637V23.9827C7.43344 23.5627 7.58688 23.2823 7.89488 23.1423L12.5572 21C11.3526 20.2595 10.7715 18.9212 10.7101 17.4308C10.7313 15.5947 11.9349 13.5927 14.1117 13.4402ZM27.887 13.4402C29.8222 13.4708 31.22 15.3894 31.2899 17.4309C31.2793 18.7817 30.752 20.0861 29.5253 20.9588L34.1038 23.1423C34.4118 23.3103 34.5652 23.5907 34.5652 23.9828V27.637C34.5606 28.0707 34.3265 28.5156 33.8099 28.5599H30.6596V24.6968C30.6596 24.1928 30.534 23.7371 30.282 23.3311C30.03 22.9251 29.694 22.6109 29.274 22.3869C27.9683 21.7801 26.4689 21.3445 25.4934 20.3285C26.2214 19.2085 26.5851 17.9624 26.5851 16.5904C26.5851 15.7224 26.4317 14.8679 26.1237 14.0279C26.6295 13.694 27.2282 13.4496 27.887 13.4402Z"
                                  fill="#536DFE"></path>
                        </g>
                        <defs>
                            <clipPath id="clip0_2_31">
                                <rect width="42" height="42" fill="white"></rect>
                            </clipPath>
                        </defs>
                    </svg>
                    <p>Users</p>
                </div>
                <div class="mt-4 mb-2 overflow-auto md:mt-10">
                    <!-- The users bean is already in the header, so we don't need to put another useBean -->
                    <%= users.getAllUsers().toHTML("transition-all flex flex-row items-center justify-between w-full p-2 border-b-2 border-blue-200 hover:border-blue-600 hover:bg-blue-50 md:w-4/6") %>
                </div>
            </div>
            <div class="flex-1 flex flex-col md:pl-4">
                <div class="flex flex-row items-center gap-3 tracking-wider">
                    <svg width="30" height="30" viewBox="0 0 42 42" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M42 21C42 26.5695 39.7875 31.911 35.8492 35.8492C31.911 39.7875 26.5695 42 21 42C15.4305 42 10.089 39.7875 6.15076 35.8492C2.21249 31.911 0 26.5695 0 21C0 15.4305 2.21249 10.089 6.15076 6.15076C10.089 2.21249 15.4305 0 21 0C26.5695 0 31.911 2.21249 35.8492 6.15076C39.7875 10.089 42 15.4305 42 21ZM10.5 21C10.5 21.3481 10.6383 21.6819 10.8844 21.9281C11.1306 22.1742 11.4644 22.3125 11.8125 22.3125H19.6875V30.1875C19.6875 30.5356 19.8258 30.8694 20.0719 31.1156C20.3181 31.3617 20.6519 31.5 21 31.5C21.3481 31.5 21.6819 31.3617 21.9281 31.1156C22.1742 30.8694 22.3125 30.5356 22.3125 30.1875V22.3125H30.1875C30.5356 22.3125 30.8694 22.1742 31.1156 21.9281C31.3617 21.6819 31.5 21.3481 31.5 21C31.5 20.6519 31.3617 20.3181 31.1156 20.0719C30.8694 19.8258 30.5356 19.6875 30.1875 19.6875H22.3125V11.8125C22.3125 11.4644 22.1742 11.1306 21.9281 10.8844C21.6819 10.6383 21.3481 10.5 21 10.5C20.6519 10.5 20.3181 10.6383 20.0719 10.8844C19.8258 11.1306 19.6875 11.4644 19.6875 11.8125V19.6875H11.8125C11.4644 19.6875 11.1306 19.8258 10.8844 20.0719C10.6383 20.3181 10.5 20.6519 10.5 21Z"
                              fill="#536DFE"></path>
                    </svg>
                    <p>Add User</p>
                </div>
                <form action="${pageContext.servletContext.contextPath}/admin/add-user" method="post" id="add-user-form"
                      class="flex flex-col mt-4 md:mt-10">
                    <label for="username" class="block text-sm font-medium text-gray-900">Username</label>
                    <input type="text" name="username" placeholder="Enter the username" id="username"
                           class="transition-all bg-gray-50 border border-gray-300 outline-0 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 p-2 mb-4">

                    <label for="password" class="block text-sm font-medium text-gray-900">Password</label>
                    <input type="text" name="password" placeholder="Enter the password" id="password"
                           class="transition-all bg-gray-50 border border-gray-300 outline-0 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 p-2 mb-2">

                    <!-- Error message got from the request -->
                    <p class="text-sm text-red-500 mb-2"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
                    </p>

                    <input type="submit" value="Create"
                           class="transition-all cursor-pointer text-white bg-blue-600 hover:bg-blue-700 focus:ring-4 focus:outline-none focus:ring-violet-300 font-medium rounded-md text-sm w-full px-5 py-2.5 text-center">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
