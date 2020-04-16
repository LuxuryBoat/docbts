<div id="myCarousel" class="carousel slide" data-ride="carousel">

    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <div class="carousel-inner">
      <div class="carousel-item active">
        <img class="bd-placeholder-img card-img-top" src="./assets/pictures/caroussel/carrou1.jpg" alt="Erreur de chargement de l'image" width="100%" height="225">
        <div class="container">
          <div class="carousel-caption text-left">
            <h1>Example headline.</h1>
            <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
          </div>
        </div>
      </div><!-- end Page 1 -->

      <div class="carousel-item">
        <img class="bd-placeholder-img card-img-top" src="./assets/pictures/caroussel/carrou2.jpg" alt="Erreur de chargement de l'image" width="100%" height="225">
        <div class="container">
          <div class="carousel-caption">
            <h1>Another example headline.</h1>
            <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
          </div>
        </div>
      </div><!-- end Page 2 -->

      <div class="carousel-item">
        <img class="bd-placeholder-img card-img-top" src="./assets/pictures/caroussel/carrou3.jpg" alt="Erreur de chargement de l'image" width="100%" height="225">
        <div class="container">
          <div class="carousel-caption text-right">
            <h1>One more for good measure.</h1>
            <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
          </div>
        </div>
      </div><!-- end Page 3 -->
    </div>
    
    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Précedent</span>
    </a>
    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Suivant</span>
    </a>
  </div>
 

  <div class="album py-5">
    <div class="container">
      
    <div class="row">
        <?php
          $products = $bdd->query("SELECT * FROM Bateau b, Boutique bo WHERE b.idBateau = bo.id_bateau AND active='confirme' LIMIT 6");
          foreach($products as $product):
        ?>
          <div class="col-md-4">
              <div class="card mb-4 shadow-sm">
                  <img class="bd-placeholder-img card-img-top" src="assets/pictures/boat/<?= $product->idBateau ?>.jpg" alt="Erreur de chargement de l'image" width="100%" height="225">
                  <div class="card-body">
                      <p class="card-text"><?= $product->nomBateau ?></p>
                      <p class="card-text"><?= substr($product->description, -20)?> ... </p>
                      <div class="d-flex justify-content-between align-items-center">
                          <div class="btn-group">
                              <a type="button" class="btn btn-sm btn-outline-secondary addPanier" href="addpanier/<?= $product->idBateau; ?>">Panier</a>
                          </div>
                          <small class="text-muted">Prix <?= number_format($product->prix,2,',',' ') ?>€</small>
                      </div>
                  </div>
              </div>
          </div>
        <!-- FIN Boucle forEach -->
        <?php endforeach ?>
    </div>

  </div>
</div>