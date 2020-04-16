<?php
  if(!empty($_SESSION)){

  if(isset($_POST["submit"]))
  {  
    $email = $_POST["email"];
    $mdp = sha1($_POST["mdp"]);
    
    $requete = $bdd->query("SELECT * FROM employerall WHERE email='$email' AND mdp='$mdp'");

    if($requete) {
        $_SESSION["admin"] = true;
        $_SESSION["admin"] = $requete[0]->id;
        header('Location: dashboard');
    } else {
        $erreur = "Mauvais identifiants";
    }
  }
?>
  <div class="container mt-2">
    <img class="rounded mx-auto d-block" src="./assets/pictures/logo/logo.png" alt="Erreur de chargement de l'image" width="300" height="300">

    <form method="POST" class="container" style="width: 30%;">
      <input type="text" class="form-control" placeholder="Email" name="email">
      <input type="password" class="form-control" placeholder="Mot de passe" name="mdp">
      <button type="submit" class="btn btn-info form-control"  name="submit">Connexion</button>
    </form>

  </div>
<?php
}else if($_SESSION['admin']) {
  header('Location: dashboard');
}else{
  header('Location: accueil');
}?>