function toggleReturnDate() {
    const returnDateInput = document.getElementById("return-date");
    const returnDateCheckbox = document.getElementById("return-date-checkbox");
  
    if (returnDateCheckbox.checked) {
      returnDateInput.disabled = false;
    } else {
      returnDateInput.disabled = true;
      returnDateInput.value = ""; // Clear the return date value if unchecked
      document.getElementById("return-date-error").style.display = "none";
    }
  }
  
  function validateDate() {
    var departureDate = document.getElementById("departure-date").value;
    var returnDate = document.getElementById("return-date").value;
  
    var today = new Date().toISOString().split("T")[0];
    var departureDateError = document.getElementById("departure-date-error");
    var returnDateError = document.getElementById("return-date-error");
  
    departureDateError.style.display = departureDate < today ? "block" : "none";
    returnDateError.style.display =
      returnDate && returnDate < departureDate ? "block" : "none";
  }
  
  // Function untuk tidak dapat memilih tanggal sebelum hari ini
  document.addEventListener("DOMContentLoaded", function () {
    var today = new Date().toISOString().split("T")[0];
    document.getElementById("departure-date").setAttribute("min", today);
    document.getElementById("return-date").setAttribute("min", today);
  
    // Tambahkan event listener untuk input tanggal pelayaran
    document
      .getElementById("departure-date")
      .addEventListener("change", function () {
        var departureDate = new Date(this.value); // Ambil nilai tanggal pelayaran yang baru dipilih
        var returnDateInput = document.getElementById("return-date");
        var returnDate = new Date(returnDateInput.value); // Ambil nilai tanggal kembali saat ini
  
        // Perbarui atribut min dari tanggal kembali
        returnDateInput.setAttribute("min", this.value);
  
        // Jika tanggal kembali saat ini lebih awal dari tanggal pelayaran yang baru dipilih, atur nilainya menjadi tanggal pelayaran
        if (returnDate < departureDate) {
          returnDateInput.value = this.value;
        }
      });
  });
  
  // Fungsi untuk mengecek input sebelum mengirimkan form
  function checkInput() {
    var fromValue = document.getElementById("portAwal").value;
    var toValue = document.getElementById("portAkhir").value;
    var departureDate = document.getElementById("departure-date").value;
    var adultsValue = document.getElementById("adults").value;
    var children = document.getElementById("children").value;
    var childrenCheckbox = document.getElementById("children-checkbox");
  
    // Convert input values to integers
    var adults = parseInt(adultsValue);
    children = parseInt(children);
  
    // Calculate the total number of adults and children
    var totalPeople = adults + children;
  
    // Limit the maximum total number of people to 7
    if (totalPeople > 7) {
      alert("The total number of adults and children cannot exceed 7.");
      return false;
    }
  
    // If the number of adults is 7 and the children checkbox is checked, display a warning
    if (adults >= 7 && childrenCheckbox.checked) {
      alert(
        "The number of adults has reached the maximum of 7. You cannot select children."
      );
      return false;
    }
  
    // Check if all required fields are filled
    if (
      fromValue === null ||
      fromValue === undefined ||
      toValue === null ||
      toValue === undefined ||
      departureDate === "" ||
      adultsValue === ""
    ) {
      alert("Please fill in all required fields before searching.");
      return false;
    }
  
    // If all conditions are met, redirect to 'tes.html'
    window.location.href = "ttiketpergi.html";
    return true;
  }
  // document.addEventListener('DOMContentLoaded', function() {
  //     const paymentMethods = document.querySelectorAll('input[name="payment_method"]');
  //     const payButton = document.getElementById('payButton');
  
  //     paymentMethods.forEach(method => {
  //         method.addEventListener('change', function() {
  //             if (method.checked) {
  //                 payButton.classList.add('bg-yellow-400', 'text-zinc-900'); // Tambahkan kelas untuk mengubah warna
  //             } else {
  //                 payButton.classList.remove('bg-yellow-400', 'text-zinc-900'); // Hapus kelas jika tidak dipilih
  //             }
  //         });
  //     });
  // });
  
  document.addEventListener("DOMContentLoaded", function () {
    const payButton = document.getElementById("payButton");
    const paymentMethods = document.getElementById("payment_method");
  
    paymentMethods.forEach((method) => {
      method.addEventListener("change", function () {
        if (method.checked) {
          payButton.classList.add("bg-yellow-400", "text-zinc-900"); // Tambahkan kelas untuk mengubah warna
        } else {
          payButton.classList.remove("bg-yellow-400", "text-zinc-900"); // Hapus kelas jika tidak dipilih
        }
      });
    });
  });
  
  function validateFullName(index) {
    const input = document.getElementById(`full-name-input-${index}`);
    const warningMessage = document.getElementById(
      `name-warning-message-${index}`
    );
    const regex = /^[a-zA-Z\s]*$/;
  
    if (!regex.test(input.value)) {
      warningMessage.classList.remove("hidden");
      document.getElementById("payButton").disabled = true;
    } else {
      warningMessage.classList.add("hidden");
    }
  }
  
  // Fungsi untuk mengecek value Dari dan Ke tidak boleh sama
  function validateForm() {
    // Initial population of dropdowns
    populateDropdowns();
  
    // Event listeners to repopulate dropdowns on change
    fromSelect.addEventListener("change", function () {
      if (fromSelect.value === toSelect.value) {
        alert(
          "Pilihan dari dan ke tidak boleh sama. Silakan pilih lokasi yang berbeda."
        );
        fromSelect.selectedIndex = 0; // Reset to default if same
      }
      populateDropdowns();
    });
  
    toSelect.addEventListener("change", function () {
      if (fromSelect.value === toSelect.value) {
        alert(
          "Pilihan dari dan ke tidak boleh sama. Silakan pilih lokasi yang berbeda."
        );
        toSelect.selectedIndex = 0; // Reset to default if same
      }
      populateDropdowns();
    });
  
    // Ensure dropdowns are correctly populated after initial check
    if (fromSelect.value === toSelect.value) {
      alert(
        "Pilihan dari dan ke tidak boleh sama. Silakan pilih lokasi yang berbeda."
      );
      fromSelect.selectedIndex = 0; // Reset to default if same
      populateDropdowns(); // Repopulate dropdowns after reset
    }
  }
  
  function populateDropdowns() {
    var fromSelect = document.getElementById("portAwal");
    var toSelect = document.getElementById("portAkhir");
    const ports = [
      "Pelabuhan Paotere, Makassar",
      "Pulau Lanjukang, Makassar",
      "Pulau Samalona, Makassar",
      "Pulau Barranglompo, Makassar",
    ];
  
    const fromValue = fromSelect.value;
    const toValue = toSelect.value;
  
    fromSelect.innerHTML = "";
    toSelect.innerHTML = "";
  
    // Populate fromSelect
    ports.forEach((port) => {
      let option = document.createElement("option");
      option.value = port;
      option.textContent = port;
      if (port !== toValue) {
        fromSelect.appendChild(option);
      }
    });
  
    // Populate toSelect
    ports.forEach((port) => {
      let option = document.createElement("option");
      option.value = port;
      option.textContent = port;
      if (port !== fromValue) {
        toSelect.appendChild(option);
      }
    });
  
    // Restore previously selected values if they are still valid
    if (ports.includes(fromValue) && fromValue !== toValue) {
      fromSelect.value = fromValue;
    }
    if (ports.includes(toValue) && toValue !== fromValue) {
      toSelect.value = toValue;
    }
  }
  
  // Initialize dropdowns with default option
  function initializeDropdowns() {
    fromSelect.innerHTML = "";
    toSelect.innerHTML = "";
    const defaultOption = new Option("Pilih Lokasi Anda", "", false, false);
    defaultOption.disabled = true;
    defaultOption.selected = true;
    defaultOption.className = "text-opacity-50";
    fromSelect.appendChild(defaultOption.cloneNode(true));
    toSelect.appendChild(defaultOption.cloneNode(true));
  }
  
  function validatePhoneNumber() {
    const phoneInput = document.getElementById("phone-input");
    const warningMessage = document.getElementById("warning-message");
    const lengthWarningMessage = document.getElementById(
      "length-warning-message"
    );
    const phoneNumber = phoneInput.value;
  
    // Check if the input contains non-numeric characters
    if (/[^0-9]/.test(phoneNumber)) {
      warningMessage.classList.remove("hidden");
      phoneInput.value = phoneNumber.replace(/[^0-9]/g, "");
    } else {
      warningMessage.classList.add("hidden");
    }
  
    // Check if the input length is between 6 and 13
    if (phoneNumber.length < 6 || phoneNumber.length > 13) {
      lengthWarningMessage.classList.remove("hidden");
    } else {
      lengthWarningMessage.classList.add("hidden");
    }
  }
  
  function toggleChildren() {
    var checkBox = document.getElementById("children-checkbox");
    var ChildrenInput = document.getElementById("children");
    ChildrenInput.disabled = !checkBox.checked;
  }
  
  function updateCounts() {
    const adultCount = localStorage.getItem("orangDewasa");
    const childCount = localStorage.getItem("anakKecil");
  
    document.getElementById("adult-count").textContent = adultCount
      ? `${adultCount} orang`
      : "0 orang";
    document.getElementById("child-count").textContent = childCount
      ? `${childCount} anak`
      : "0 orang";
  }
  
  // Call the function on page load
  document.addEventListener("DOMContentLoaded", updateCounts);
  
  function showEmailInput() {
    const name = document.getElementById("name").value;
    if (name) {
      $("#nameModal").modal("hide");
      $("#emailModal").modal("show");
    } else {
      alert("Nama harus diisi");
    }
  }
  
  // Function to show the password input modal
  function showPasswordInput() {
    const email = document.getElementById("inputEmail").value;
    if (email) {
      $("#emailModal").modal("hide");
      $("#passwordModal").modal("show");
    } else {
      alert("E-mail harus diisi dengan benar");
    }
  }
  
  function showInfoInput() {
    const email = document.getElementById("inputEmail").value;
    if (email) {
      $("#emailModal").modal("hide");
      $("#passwordModal").modal("show");
    } else {
      alert("E-mail harus diisi dengan benar");
    }
  }
  
  // $(document).ready(function() {
  //     $('#nameModal').modal('show');
  // });
  
  function checkEmail() {
    const emailInput = document.getElementById("inputEmail");
    const nextButton = document.getElementById("nextButton");
    const emailError = document.getElementById("emailError");
    const emailPattern = emailInput.pattern;
  
    const regex = new RegExp(emailPattern);
    if (regex.test(emailInput.value)) {
      nextButton.disabled = false;
      emailError.style.display = "none";
      // emailGlobal = emailInput.value;
      console.log("isi value: " + emailInput.value);
    } else {
      nextButton.disabled = true;
      emailError.style.display = "block";
    }
  }
  
  // Fetch data login index.html
  function handleButtonLogin(event) {
    event.preventDefault(); // Mencegah pengiriman form
    var formData = {
      email: document.getElementById("email").value,
      password: document.getElementById("password").value,
    };
    console.log(JSON.stringify(formData));
  
    $.ajax({
      url: "https://sf785k8l-8080.asse.devtunnels.ms/api/login/user",
      type: "POST",
      dataType: "json",
      data: {
        email: formData.email,
        password: formData.password,
      },
      success: (result) => {
        console.log(result);
        localStorage.setItem("email", formData.email); // Add email to local storage upon successful login
      },
      error: (err) => {
        JSON.parse(err.responseText);
      },
    });
    // tambah function untuk fetch ke API dengan Method POst
  }
  
  // Fetch data register index.html
  function handleButtonConfirm(event) {
    event.preventDefault(); // Mencegah pengiriman form
    var formData = {
      name: document.getElementById("name").value,
      email: document.getElementById("inputEmail").value,
      password: document.getElementById("inputPassword").value,
    };
    console.log(JSON.stringify(formData));
  
    $.ajax({
      url: "https://sf785k8l-8080.asse.devtunnels.ms/api/register/user",
      type: "POST",
      dataType: "json",
      data: {
        name: formData.name,
        email: formData.email,
        password: formData.password,
      },
      success: (result) => {
        console.log(result);
        localStorage.setItem("email", formData);
        // isi sesuai data yang ada di dalam result
      },
      error: (err) => {
        JSON.parse(err.responseText);
      },
    });
    // tambah function untuk fetch ke API dengan Method POst
  }
  
  function fetchLocations() {
    $.ajax({
      url: "https://sf785k8l-8080.asse.devtunnels.ms/api/origin/all",
      method: "GET",
      dataType: "json",
      success: function (data) {
        console.log(data);
        var portAwal = $("#portAwal");
        var portAkhir = $("#portAkhir");
        portAwal.empty();
        portAkhir.empty();
        portAwal.append(
          '<option value="" disabled selected>Pilih Lokasi Anda</option>'
        );
        portAkhir.append(
          '<option value="" disabled selected>Pilih Lokasi Anda</option>'
        );
  
        if (data.payload && data.payload.origin) {
          $.each(data.payload.origin, function (key, entry) {
            portAwal.append(
              $("<option></option>")
                .attr("value", entry.originName)
                .text(entry.originName)
            );
            portAkhir.append(
              $("<option></option>")
                .attr("value", entry.origin)
                .text(entry.originName)
            );
          });
        } else {
          console.error("Unexpected data structure:", data);
        }
      },
      error: function (error) {
        console.error("Error fetching locations:", error);
      },
    });
  }
  
  $(document).ready(function () {
    fetchLocations();
  });
  
  document.addEventListener("DOMContentLoaded", function () {
    const paymentMethods = document.querySelectorAll(
      'input[name="paymentMethod"]'
    );
    const creditCardInfo = document.getElementById("credit-card-info");
  
    function toggleCreditCardInfo() {
      const selectedMethod = document.querySelector(
        'input[name="paymentMethod"]:checked'
      );
      if (selectedMethod && selectedMethod.id === "cash") {
        creditCardInfo.style.display = "none";
      } else {
        creditCardInfo.style.display = "block";
      }
    }
  
    paymentMethods.forEach((method) => {
      method.addEventListener("change", toggleCreditCardInfo);
    });
  
    // Initial check on page load
    if (creditCardInfo) {
      toggleCreditCardInfo();
    }
  });
  
  const form = document.getElementById("bookingForm");
  form.addEventListener("submit", function (e) {
    e.preventDefault();
    const port_Awal = document.getElementById("portAwal");
    const port_Akhir = document.getElementById("portAkhir");
    const tanggalPelayaran = document.getElementById("departure-date");
    const tanggalKembali = document.getElementById("return-date");
    const orangDewasa = document.getElementById("adults");
    const anakKecil = document.getElementById("children");
  
    console.log("awal " + port_Awal.value);
    console.log(port_Akhir.value);
    console.log(tanggalPelayaran.value);
    console.log(tanggalKembali.value);
    console.log(orangDewasa.value);
    console.log(anakKecil.value);
  
    localStorage.setItem("port-awal", port_Awal.value);
    localStorage.setItem("port-akhir", port_Akhir.value);
    localStorage.setItem("tanggalPelayaran", tanggalPelayaran.value);
    localStorage.setItem("tanggalKembali", tanggalKembali.value);
    localStorage.setItem("orangDewasa", orangDewasa.value);
    localStorage.setItem("anakKecil", anakKecil.value);
  });
  
  
  // document.getElementById("adult-count").textContent = `${adults} orang`;
  // document.getElementById("child-count").textContent = `${children} orang`;
  
  const tanggalKembaliStored = localStorage.getItem("tanggalKembali");
  const tanggalPelayaranStored = localStorage.getItem("tanggalPelayaran");
  
  // page tes.html
  function updateDate(departureTime, element) {
    // Logic for updating date-related buttons
    console.log(`Date button clicked: ${departureTime}`);
    const dateButtons = document.querySelectorAll(".d-flex.gap-2.mt-2 .btn");
    dateButtons.forEach((btn) => btn.classList.remove("btn-active"));
    element.classList.add("btn-active");
  }
  
  function changeTime(departureTime, id) {
    document.getElementById("departure-time-" + id).innerText = departureTime;
    // Calculate arrival time based on the selected departure time
    let arrivalTime = "";
    let jam = parseInt(departureTime.split(":")[0]) + 2;
      arrivalTime = ((jam.toString().length == 1) ? "0" + jam : jam) + ":" + departureTime.split(":")[1];
    document.getElementById("arrival-time-" + id).innerText = arrivalTime;
    localStorage.setItem('arrivalTimePulang', arrivalTime);
  
    // Change button color
    const buttons = document.querySelectorAll(
      `.mt-2.d-flex.gap-2 .btn-secondary`
    );
    buttons.forEach((btn) => btn.classList.remove("btn-active"));
    element.classList.add("btn-active");
  
    // Animate line
    const line = document.getElementById("animated-line-" + id);
    line.classList.remove("line-animate");
    // Trigger reflow to restart animation
    void line.offsetWidth;
    line.classList.add("line-animate");
  }
  function changeTimePergi(departureTime, id) {
    document.getElementById("departure-time-" + id).innerText = departureTime;
    // Calculate arrival time based on the selected departure time
    let arrivalTime = "";
    let jam = parseInt(departureTime.split(":")[0]) + 2;
      arrivalTime = ((jam.toString().length == 1) ? "0" + jam : jam) + ":" + departureTime.split(":")[1];
    document.getElementById("arrival-time-" + id).innerText = arrivalTime;
    localStorage.setItem('arrivalTimePergi', arrivalTime);
  
    // Change button color
    const buttons = document.querySelectorAll(
      `.mt-2.d-flex.gap-2 .btn-secondary`
    );
    buttons.forEach((btn) => btn.classList.remove("btn-active"));
    element.classList.add("btn-active");
  
    // Animate line
    const line = document.getElementById("animated-line-" + id);
    line.classList.remove("line-animate");
    // Trigger reflow to restart animation
    void line.offsetWidth;
    line.classList.add("line-animate");
  }
  
  // value jam untuk tombol pergi pulang
  var tombol = false;
  function handleButtonTimePergi(element) {
    localStorage.setItem(
      "datetimePergi",
      element.getAttribute("data-datetime")
    );
    tombol = true;
    updateTimePergi();
  }
  
  function handleButtonTime(element) {
    localStorage.setItem("datetimePulang", element.getAttribute("data-datetime"));
    tombol = true;
    updateTime();
  }
  
  function updateTimePergi() {
      document.getElementById('departure-time-1').innerText = localStorage.getItem('datetimePergi');
      changeTimePergi(localStorage.getItem('datetimePergi'), '1')
  }
  
  function updateTime() {
      document.getElementById('departure-time-1').innerText = localStorage.getItem('datetimePulang');
      changeTime(localStorage.getItem('datetimePulang'), '1')
  }
  
  function handleButtonDetail() {
  const searchButton = document.getElementById("searchButton");
    if (searchButton.disabled || !tombol) {
      return;
    }
    const tanggalKembaliStored = localStorage.getItem("tanggalKembali");
    const tanggalPelayaranStored = localStorage.getItem("tanggalPelayaran");
    if (tanggalPelayaranStored && tanggalKembaliStored) {
      window.location.href = "ttiketpulang.html";
    } else {
      window.location.href = "booking.html";
    }
  }
  
  function handleButtonClose(elementId) {
    const element = document.getElementById(elementId);
    if (element) {
      element.classList.remove("open");
    } else {
      console.error(`Element with id "${elementId}" not found.`);
    }
  }
  
  function handleButtonRiwayat() {
    window.location.href = "booking.html";
  }
  
  function allInputsFilled() {
    let allFilled = true;
  
    // Mendapatkan semua elemen input dengan id yang dimulai dengan "PilihTiketButton-"
    let inputs = document.querySelectorAll('[id^="PilihTiketButton-"]');
  
    inputs.forEach((input) => {
      if (input.value.trim() === "") {
        allFilled = false;
        return; // Keluar dari loop forEach karena sudah ada input yang kosong
      }
    });
  
    return allFilled;
  }
  
  function moveToPembayaran() {
    if (allInputsFilled()) {
      console.log("saya di click");
      var orderID = Math.floor(Math.random() * 9000000000) + 1000000000;
      localStorage.setItem("orderID", orderID);
      window.location.href = "Pembayaran.html";
    }
  }
  
  function handleButtonVA() {
    window.location.href = "VirtualAccount.html";
    storeAllDetails();
  }
  
  function storeAllDetails() {
    const orderID = localStorage.getItem("orderID");
    const details = {
      namaDepan: localStorage.getItem("namaDepan"),
      bookings: localStorage.getItem("bookings"),
      orangDewasa: localStorage.getItem("orangDewasa"),
      anakKecil: localStorage.getItem("anakKecil"),
      email: localStorage.getItem("email"),
    };
  
    const detailsString = JSON.stringify(details);
    localStorage.setItem(`allDetails-${orderID}`, detailsString);
  }
  
  function handleButtonRiwayatPesanan() {
    window.location.href = "riwayatorder.html";
  }
  
  function movetoIndex() {
    window.location.href = "iindex.html";
  }
  
  function showPayment() {
    const paymentSection = document.getElementById("payment-section");
    paymentSection.classList.toggle("open");
  }
  
  function showCheck() {
    const checkModal = document.getElementById("checkModal");
    checkModal.classList.toggle("open");
  }
  
  function showModal() {
    const email = localStorage.getItem("namaDepan");
  
    if (email !== null) {
      const showdetail = document.getElementById("show-detail");
      showdetail.classList.toggle("open");
      console.log("ter klik ji");
  
      const paymentSection = document.getElementById("payment-section");
      if (paymentSection) {
        paymentSection.classList.remove("open");
      } else {
        console.error('Element with id "payment-section" not found.');
      }
    } else {
      console.log("login dulu");
    }
  }
  
  function swapValues() {
    var dari = document.getElementById("portAwal");
    var ke = document.getElementById("portAkhir");
    if (dari.value !== ke.value) {
      var temp = dari.value;
      dari.value = ke.value;
      ke.value = temp;
    } else {
      alert("Dari dan Ke tidak boleh sama!");
    }
  }
  
  var today = new Date().toISOString().split("T")[0];
  document.getElementById("tanggalPelayaran").setAttribute("min", today);
  document.getElementById("tanggalKembali").setAttribute("min", today);
  
  // Fungsi untuk memperbarui nilai jumlah orang dewasa
  function updateAdultsValue() {
    var adults = document.getElementById("adults").value;
    var childrenCheckbox = document.getElementById("children-checkbox");
    var childrenSelect = document.getElementById("children");
  
    // Konversi nilai adults menjadi integer
    adults = parseInt(adults);
  
    // Jumlah maksimum orang dewasa yang diizinkan
    var maxAdults = 7;
  
    // Jika jumlah orang dewasa mencapai atau melebihi 7
    if (adults >= maxAdults) {
      // Nonaktifkan checkbox anak-anak
      childrenCheckbox.disabled = true;
      // Kembalikan nilai checkbox anak-anak ke false dan nonaktifkan select
      childrenCheckbox.checked = false;
      childrenSelect.disabled = true;
      childrenSelect.selectedIndex = 0; // Kembalikan pilihan ke default (0 anak)
    } else {
      // Aktifkan checkbox anak-anak kembali
      childrenCheckbox.disabled = false;
      // Aktifkan select untuk memilih jumlah anak-anak
      childrenSelect.disabled = !childrenCheckbox.checked;
    }
  
    // Panggil fungsi updateAdultsValue untuk mengatur ulang opsi dropdown anak-anak
    updateChildrenOptions();
  }
  
  // Fungsi untuk memperbarui opsi jumlah anak-anak berdasarkan jumlah orang dewasa yang dipilih
  function updateChildrenOptions() {
    var adults = document.getElementById("adults").value;
    var childrenSelect = document.getElementById("children");
  
    // Ambil nilai maksimum anak-anak berdasarkan jumlah orang dewasa yang dipilih
    var maxChildren = 7 - adults;
  
    // Hapus opsi yang lebih besar dari nilai maksimum
    while (childrenSelect.options.length > maxChildren + 1) {
      childrenSelect.remove(childrenSelect.options.length - 1);
    }
  
    // Tambahkan kembali opsi yang diperlukan
    for (var i = childrenSelect.options.length; i <= maxChildren; i++) {
      var option = document.createElement("option");
      option.value = i;
      option.textContent = i + " Anak";
      childrenSelect.appendChild(option);
    }
  }
  
  function validateFullName(index) {
    const inputElement = document.getElementById(`PilihTiketButton-${index}`);
    const warningMessage = document.getElementById(
      `name-warning-message-${index}`
    );
    const inputValue = inputElement.value.trim();
  
    if (/^[A-Za-z\s]*$/.test(inputValue)) {
      inputElement.setCustomValidity("");
      warningMessage.classList.add("hidden");
    } else {
      inputElement.setCustomValidity("Nama harus berupa huruf dan spasi saja.");
      warningMessage.classList.remove("hidden");
    }
  }
  
  function checkNameInputs() {
    const PilihTiketButton = document.getElementById("PilihTiketButton");
    const inputElements = document.querySelectorAll(
      'input[id^="PilihTiketButton-"]'
    );
    let allValid = true;
  
    inputElements.forEach((input) => {
      if (/^[A-Za-z\s]*$/.test(input.value.trim())) {
        allValid = true;
        input.classList.add("btn-warning", "w-100"); // Change input background color to yellow if input is correct
      } else {
        allValid = false;
        input.classList.add("btn-warning", "w-100"); // Change input background color to yellow if input is correct
      }
    });
  
    PilihTiketButton.disabled = !allValid;
  }
  
  // Memanggil validateFullName dan checkNameInputs pada setiap perubahan input
  document.addEventListener("DOMContentLoaded", () => {
    const inputElements = document.querySelectorAll(
      'input[id^="PilihTiketButton-"]'
    );
  
    inputElements.forEach((input, index) => {
      input.addEventListener("input", () => {
        validateFullName(index);
        checkNameInputs();
      });
    });
  });
  
  var tombol = false;
function handleButtonTimePergi(element) {
  localStorage.setItem(
    "datetimePergi",
    element.getAttribute("data-datetime")
  );
  tombol = true;
  updateTimePergi();
}

function handleButtonTime(element) {
  localStorage.setItem("datetimePulang", element.getAttribute("data-datetime"));
  tombol = true;
  updateTime();
}

function handleButtonTimeSampaiKapal(element) {
    localStorage.setItem("arrivalTimePergi", element.getAttribute("data-datetime"));
    // tombol = true;
    // updateTime();
  }

function updateTimePergi() {
    document.getElementById('departure-time-1').innerText = localStorage.getItem('datetimePergi');
    changeTimePergi(localStorage.getItem('datetimePergi'), '1')
}

function updateTime() {
    document.getElementById('departure-time-1').innerText = localStorage.getItem('datetimePulang');
    changeTime(localStorage.getItem('datetimePulang'), '1')
}

function handleButtonDetail() {
const searchButton = document.getElementById("searchButton");
  if (searchButton.disabled || !tombol) {
    return;
  }
  const tanggalKembaliStored = localStorage.getItem("tanggalKembali");
  const tanggalPelayaranStored = localStorage.getItem("tanggalPelayaran");
  if (tanggalPelayaranStored && tanggalKembaliStored) {
    window.location.href = "ttiketpulang.html";
  } else {
    window.location.href = "booking.html";
  }
}
  // // Function to handle the button click
  // PilihTiketButton.addEventListener('click', function() {
  //     if (allInputsValid()) {
  //         console.log("dasda")
  //         window.location.href = 'Pembayaran.html';
  //         var orderID = Math.floor(Math.random() * 9000000000) + 1000000000;
  //             localStorage.setItem('orderID', orderID);
  //     }
  // });
  