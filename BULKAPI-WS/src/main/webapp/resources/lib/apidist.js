/**
 * 
 */
$(document).ready(function() {
	$('#submitTouchCash').submit(function(e) {
		console.log('OK')
        var frm = $('#submitTouchCash');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: "PUT",
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    //alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+"  Role: "+callback.role);
                    //$(this).html("Action effectuee avec succes");
                    $.notify("Action effectuee avec succes", "success");
                } else {
                    //$('#settingsMessageSuccess').html(callback.msg);
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $('#settingsMessageFailed').html("Une erreur est survenue pendant l'ajout des Parametres de messagerie");
                $.notify("Une erreur est survenue pendant l'ajout des Parametres de messagerie ", "error");
            }
        });
    });
});