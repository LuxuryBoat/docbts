<?php 
if(isset($_SESSION['admin'])){


  if(isset($_POST['deletecategorie']))
  {
      $supp = $_POST['suppCategorie'];
      $requete = $bdd->query("DELETE FROM Categorie WHERE idCategorie =".$supp);
  }

  if(isset($_POST['addcategorie']))
  {
      $nom = $_POST['nomcat'];
      $requete = $bdd->query("INSERT INTO Categorie(nomCategorie) VALUES('$nom')");
  }
?>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Dashboard</h1>
    </div>

    <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>

      <div class="row">
        <div class="col-sm">
          <div class="card" style="width: 18rem;">
            <div class="card-header">
              Ajouter catégorie
            </div>
            <form method="post">
              <div class="p-1">
                  <input type="text" class="form-control" placeholder="Nom catégorie" name="nomcat">
              </div>
              <button type="submit" name="addcategorie" class="btn btn-success m-1" style="width: 10rem;">Ajouter</button>
            </form>
          </div>
        </div>
        <div class="col-sm">
        </div>
        <div class="col-sm">
          <div class="card" style="width: 18rem;">
            <div class="card-header">
              Supprimer catégorie
            </div>
            <form method="post">
              <select class="form-control m-2" multiple name="suppCategorie" style="width: 270px;">
                    <?php
                        $categories = $bdd->query("SELECT * FROM Categorie");
                        foreach($categories as $categorie):
                    ?>
                        <option value="<?= $categorie->idCategorie ?>"><?= $categorie->idCategorie ?>- <?= $categorie->nomCategorie ?></option>
                    <?php endforeach; ?>
              </select>
              <button type="submit" name="deletecategorie" class="btn btn-danger m-1" style="width: 10rem;">Supprimer</button>
            </form>
          </div>
        </div>
      </div>

      <h2>Bateau ajouter</h2>
            <div class="table-responsive">
                <table class="table table-striped table-sm" id="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Bateau</th>
                            <th>Prix</th>
                            <th>Status</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <?php 
                        $requetes = $bdd->query("SELECT * FROM Bateau");
                        foreach($requetes as $requete):
                        
                            $requets = $bdd->query("SELECT * FROM Boutique WHERE id_bateau =".$requete->idBateau);
                            foreach($requets as $requet):
                            
                                if($requet->active === 'confirme')
                                {
                    ?>
                          <tr>
                            <td><?= $requete->idBateau ?></td>
                            <td><?= $requete->nomBateau ?></td>
                            <td><?= $requete->prix ?> €</td>
                            <td><?= $requet->active ?></td>
                            <td><button class="btn btn-success" data-toggle="collapse" data-target="#addBoutique<?= $requete->idBateau?>">Modifier</button></td>
                            <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete<?= $requete->idBateau?>">Supprimer</button></td>
                        </tr>
        
                            <tr id="addBoutique<?= $requete->idBateau?>" class="collapse">
                                <form method="POST">
                                    <td><?= $requete->idBateau?></td>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" value="<?= $requete->nomBateau?>" name="nom">
                                        </div>   
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" value="<?= $requete->prix?>" name="prix">
                                        </div>   
                                    </td>
                                    <td>
                                        <select class="form-control" name="status">
                                            <option value="confirme">Confirme</option>
                                            <option value="non-confirme">non-confirme</option>
                                        </select>
                                    </td>
                                    <td></td>
                                    <td>
                                    <div class="form-group">
                                          <input type="hidden"  name="idBoutique" value="<?= $requet->idBoutique ?>"/>
                                          <input type="hidden"  name="idBateau" value="<?= $requete->idBateau ?>"/>
                                          <button type="submit" class="btn btn-warning" style="color: white;" name="update">Modifier</button>
                                      </div>   
                                    </td>
                                </form>
                            </tr>

                            <!-- Modal -->
                            <div class="modal fade" id="delete<?= $requete->idBateau?>" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="deleteModalLabel">Supprimer le bateau <?= $requete->nomBateau ?></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Etes vous sur de vouloir supprimer le bateau <?= $requete->nomBateau?> ?
                                        </div>
                                        <div class="modal-footer">
                                            <form method="POST">
                                                <input type="hidden" value="<?= $requete->idBateau ?>" name="suppBateau"/>
                                                <button type="input" class="btn btn-danger" name="delete">Supprimer</button>
                                            </form>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    <?php   
                                }
                            endforeach;
                        endforeach;
                    ?>
                </tbody>
            </table>

</main>


<script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
<script>

(function () {
  'use strict'

  feather.replace()

  // Graphs
  var ctx = document.getElementById('myChart')
  // eslint-disable-next-line no-unused-vars
  var myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [
        <?php
            $categories = $bdd->query("SELECT * FROM Categorie");
            foreach($categories as $categorie):
        ?>
            <option value="<?= $categorie->idCategorie ?>"><?= $categorie->idCategorie ?>- <?= $categorie->nomCategorie ?></option>
        <?php endforeach; ?>
      ],
      datasets: [{
        data: [
          <?php 
          $requete = $bdd->query("SELECT * FROM Bateau");
          foreach($requete as $row):
        ?>
          <?= count($row->idCategorie) ?>,
        <?php
          endforeach;
        ?>
        ],
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
}())</script>

<?php
}else{
    header('Location: accueil');
}
?>