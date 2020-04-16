<header>
  <?php 
    if(!isset($_SESSION['admin'])) { 
  ?>

  <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal">Luxury Boat</h5>
    <nav class="my-2 my-md-0 mr-md-3">
      <a class="p-2 text-dark" href="accueil">Accueil</a>
      <a class="p-2 text-dark" href="boutique">Boutique</a>
      <a class="p-2 text-dark" href="contact">Contact</a>
      <?php
        if(!isset($_SESSION['users'])) {
      ?>
        <a class="p-2 text-dark" href="panier">Panier</a>
        <a class="btn btn-outline-secondary mr-2" href="inscription">Inscription</a>
        <a class="btn btn-outline-primary" href="connexion">Connexion</a>
      <?php
        } else if(isset($_SESSION['users'])) {
      ?>
        <a class="p-2 text-dark" href="panier">Panier</a>
        <a class="p-2 text-dark" href="mescommandes">Mes commandes</a>
        <a class="btn btn-outline-secondary mr-2" href="profil">Profil</a>
        <a class="btn btn-outline-primary" href="deconnexion">Déconnexion</a>
      <?php } ?>
    </nav>
  </div>
    

  <?php
    } else if(isset($_SESSION['admin'])) { 
  ?>
  <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="index.php">Luxury Boat</a>

    <input class="form-control form-control-dark w-100" type="text" placeholder="Recherche" aria-label="Search">
    <ul class="navbar-nav px-3">
      <li class="nav-item text-nowrap">
        <a class="nav-link" href="deconnexion">Déconnexion</a>
      </li>
    </ul>
  </nav>

  <div class="container-fluid">
    <div class="row">

      <nav class="col-md-2 d-none d-md-block bg-light sidebar">
        <div class="sidebar-sticky">

          <ul class="nav flex-column">
            <li class="nav-item">
              <a class="nav-link" href="dashboard">
                <span data-feather="home"></span>
                Dashboard
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="ausers">
                <span data-feather="users"></span>
                Utilisateurs
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="aboutique">
                <span data-feather="shopping-cart"></span>
                Boutique
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="acommande">
                <span data-feather="bar-chart-2"></span>
                Commandes
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="abateau">
                <span data-feather="file"></span>
                Ajouter Bateau
              </a>
            </li>
          </ul>

        </div>
      </nav>
  
    </div>
  </div>
  <?php 
    }
  ?>
</header>