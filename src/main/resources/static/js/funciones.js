function resetDatos_personales(){
	var nombre = document.getElementById("nombre");
	var apellido = document.getElementById("apellidos");
	var fechaNacimiento = document.getElementById("fechaNacimiento");
	if (nombre != null){
		nombre.value = null;
	}
	if (apellido != null){
		apellido.value = null;
	}
	if (fechaNacimiento != null){
		fechaNacimiento.value = null;
	}
	resetRadioButtons("generoSeleccionado");
}
function resetDatos_contacto(){
	var telefonoMovil = document.getElementById("telefonoMovil");
	var documento = document.getElementById("documento");
	var nombreDireccion = document.getElementById("nombreDireccion");
	var numero = document.getElementById("numero");
	var portal = document.getElementById("portal");
	var planta = document.getElementById("planta");
	var puerta = document.getElementById("puerta");
	var localidad = document.getElementById("localidad");
	var region = document.getElementById("region");
	var codigoPostal = document.getElementById("codigoPostal");
	if (telefonoMovil != null){
		telefonoMovil.value = null;
	}
	if (documento != null){
		documento.value = null;
	}
	if (nombreDireccion != null){
		nombreDireccion.value = null;
	}
	if (numero != null){
		numero.value = null;
	}
	if (portal != null){
		portal.value = null;
	}
	if (planta != null){
		planta.value = null;
	}
	if (puerta != null){
		puerta.value = null;
	}
	if (localidad != null){
		localidad.value = null;
	}
	if (region != null){
		region.value = null;
	}
	if (codigoPostal != null){
		codigoPostal.value = null;
	}
}
function resetDatos_cliente(){
	var comentarios = document.getElementById("comentarios");
	var aceptaLicencia = document.getElementById("aceptaLicencia");
	if (comentarios != null){
		comentarios.value = null;
	}
	if (aceptaLicencia != null){
		aceptaLicencia.checked = null;
	}
	resetCheckBox("categoriasSeleccionadas", false);
}

function resetRadioButtons(buttons){
	var radioButtons = document.getElementsByName(buttons);
	for (var i = 0; i < radioButtons.length; i++){
		if (radioButtons[i].type == "radio"){
			radioButtons[i].checked = false;
		}
	}
}
function selectPrimerRadio(buttons){
	var radioButtons = document.getElementsByName(buttons);
	if (radioButtons[0].type == "radio"){
		radioButtons[0].checked = true;
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
function triger(selector){
	document.getElementsByTagName("link")[0].href = selector.value;
}
