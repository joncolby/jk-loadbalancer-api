<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
            "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dialog demo</title>
    <style type="text/css">
        @import "/tomcatmaster/js/dojo/1.1.1/dijit/themes/tundra/tundra.css";
        @import "/tomcatmaster/js/dojo/1.1.1/dojo/resources/dojo.css"
    </style>
    <script type="text/javascript" src="/tomcatmaster/js/dojo/1.1.1/dojo/dojo.js.uncompressed.js"
        djConfig="parseOnLoad: true"></script>
    <script type="text/javascript">
       dojo.require("dojo.parser");
       dojo.require("dijit.form.Button");
       dojo.require("dijit.Dialog");
       dojo.require("dijit.form.TextBox");
       function checkPw(dialogFields) {
          if (dialogFields.confirmpw != dialogFields.newpw)
             alert("Confirmation password is different.  Password is unchanged.");
       }
     </script>
</head>
<body class="tundra">
<button dojoType="dijit.form.Button" onclick="dijit.byId('dialog1').show()">Change Password</button>
<div dojoType="dijit.Dialog" id="dialog1" title="First Dialog" execute="checkPw(arguments[0]);">
    <table>
        <tr>
        <td><label for="name">Old Password: </label></td>
                <td><input dojoType="dijit.form.TextBox" type="password" name="oldpw"></td>
        </tr>
        <tr>
                <td><label for="loc">New Password: </label></td>
                <td><input dojoType="dijit.form.TextBox" type="password" name="newpw"></td>
        </tr>
        <tr>
                <td><label for="desc">Confirm New Password: </label></td>
                <td><input dojoType="dijit.form.TextBox" type="password" name="confirmpw"></td>
        </tr>
        <tr>
                <td colspan="2" align="center">
                        <button dojoType=dijit.form.Button type="submit">OK</button></td>
        </tr>
    </table>
</div>
</body></html>