document.addEventListener('DOMContentLoaded', function() {
    const fromSelect = document.getElementById('from');
    const toSelect = document.getElementById('to');

    function updatePlaceholderStyle(selectElement) {
        if (selectElement.value === '') {
            selectElement.classList.add('placeholder');
        } else {
            selectElement.classList.remove('placeholder');
        }
    }

    // Initial check
    updatePlaceholderStyle(fromSelect);
    updatePlaceholderStyle(toSelect);

    // Add event listeners
    fromSelect.addEventListener('change', function() {
        updatePlaceholderStyle(fromSelect);
    });

    toSelect.addEventListener('change', function() {
        updatePlaceholderStyle(toSelect);
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const iconDropdown = document.querySelector('.icon-right');
    const selectElement = document.querySelector('#to');

    iconDropdown.addEventListener('click', function() {
        selectElement.focus(); // Memfokuskan select element untuk memunculkan dropdown
    });

    selectElement.addEventListener('focus', function() {
        // Menambahkan event focus untuk memastikan dropdown muncul
        this.size = this.length;
    });

    selectElement.addEventListener('blur', function() {
        // Menambahkan event blur untuk menghilangkan dropdown saat kehilangan fokus
        this.size = 1;
    });

    selectElement.addEventListener('change', function() {
        // Menambahkan event change untuk menghilangkan dropdown setelah memilih opsi
        this.size = 1;
    });
});