document.addEventListener('DOMContentLoaded', function() {
    const openFormBtn = document.getElementById('openFormBtn');
    const popupForm = document.getElementById('popupForm');
    const closeFormBtn = document.getElementById('closeFormBtn');
    const registrationForm = document.getElementById('registrationForm');
    const username = document.getElementById('username');
    const password = document.getElementById('password');
    const usernameError = document.getElementById('usernameError');
    const passwordError = document.getElementById('passwordError');

    openFormBtn.addEventListener('click', function() {
        popupForm.style.display = 'flex';
    });

    closeFormBtn.addEventListener('click', function() {
        popupForm.style.display = 'none';
    });

    window.addEventListener('click', function(event) {
        if (event.target == popupForm) {
            popupForm.style.display = 'none';
        }
    });

    registrationForm.addEventListener('submit', function(event) {
        let isValid = true;

        if (username.value.length < 6) {
            usernameError.style.display = 'block';
            isValid = false;
        } else {
            usernameError.style.display = 'none';
        }

        const passwordPattern = /^[a-zA-Z0-9]{6,}$/;
        if (!passwordPattern.test(password.value)) {
            passwordError.style.display = 'block';
            isValid = false;
        } else {
            passwordError.style.display = 'none';
        }

        if (!isValid) {
            event.preventDefault();
        }
    });
});