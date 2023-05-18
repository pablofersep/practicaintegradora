function resetAll(mensaje){
	var nombre = document.getElementById("nombre");
	var clavePrincipal = document.getElementById("clave");
	var claveConfirmada = document.getElementById("confirmarClave");
	var fechaNacimiento = document.getElementById("fechaNacimiento");
	var edad = document.getElementById("edad");
	var telefonoMovil = document.getElementById("telefonoMovil");
	var email = document.getElementById("email");
	var comentarios = document.getElementById("comentarios");
	var licencia = document.getElementById("licencia");
	if(confirm(mensaje)){
		if (nombre != null){
			nombre.value = null;
		}
		if (clavePrincipal != null){
			clavePrincipal.value = null;
		}
		if (claveConfirmada != null){
			claveConfirmada.value = null;
		}
		if (fechaNacimiento != null){
			fechaNacimiento.value = null;
		}
		if (edad != null){
			edad.value = null;
		}
		if (telefonoMovil != null){
			telefonoMovil.value = null;
		}
		if (email != null){
			email.value = null;
		}
		if (comentarios != null){
			comentarios.value = null;
		}
		if (licencia != null){
			licencia.checked = false;
		}
		resetRadioButtons("generoSeleccionado");
		resetSelect("musicasSeleccionadas", false);
		resetCheckBox("aficionesSeleccionadas", false);
	}		
}

function resetRadioButtons(buttons){
	var radioButtons = document.getElementsByName(buttons);
	for (var i = 0; i < radioButtons.length; i++){
		if (radioButtons[i].type == "radio"){
			radioButtons[i].checked = false;
		}
	}
}

function resetSelect(select, mode){
	var selectTag = document.getElementById(select).options;
	if (mode == true){
		for (var i = 0; i < selectTag.length; i++){
			selectTag[i].selected = true;
		}
	}else {
		for (var i = 0; i < selectTag.length; i++){
			selectTag[i].selected = false;
		}
	}
}

function resetCheckBox(checkBox, mode){
	var checkBoxTag = document.getElementsByName(checkBox);
	if (mode == true){
		for (var i = 0; i < checkBoxTag.length; i++){
			checkBoxTag[i].checked = true;
		}
	}else {
		for (var i = 0; i < checkBoxTag.length; i++){
			checkBoxTag[i].checked = false;
		}
	}
}

function showPassword(password, passwordCheck){
	var passwordBox = document.getElementById(password);
	var passwordBoxCheck = document.getElementById(passwordCheck);
	passwordBox.type = "text";
	passwordBoxCheck.type = "text";
}
function hidePassword(password, passwordCheck){
	var passwordBox = document.getElementById(password);
	var passwordBoxCheck = document.getElementById(passwordCheck);
	passwordBox.type = "password";
	passwordBoxCheck.type = "password";
}
