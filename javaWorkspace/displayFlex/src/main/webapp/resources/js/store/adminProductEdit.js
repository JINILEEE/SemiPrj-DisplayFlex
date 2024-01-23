/**
 * 
 */
function previewImage(event) {
    const input = event.target;
    const preview = document.getElementById('imgPreview');
    const plusIcon = document.querySelector('.fa-circle-plus');

    // Check if a file was selected
    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            // Set the source of the preview div to the data URL of the selected image
            preview.src = e.target.result;
            // Set other styles if needed
            preview.style.width = '400px'; // Adjust width as needed
            preview.style.height = '400px'; // Adjust height as needed
            };

        // Read the selected file as a data URL
        reader.readAsDataURL(input.files[0]);
        plusIcon.style.opacity=0;
    } else {
        // Clear the preview if no file is selected
        preview.style.backgroundImage = '';
    }
}
