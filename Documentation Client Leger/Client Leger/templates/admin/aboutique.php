<?php 
if(isset($_SESSION['admin'])){
    if(isset($_POST['update']))
    {
        $nom = $_POST['nom'];
        $prix = $_POST['prix'];
        $status = $_POST['status'];
        $idBateau = $_POST['idBateau'];
        $idBoutique= $_POST['idBoutique'];
    
        $requete = $bdd->query("UPDATE Boutique SET active = '$status', id_bateau = '$idBateau' WHERE idBoutique = $idBoutique");
        $reque = $bdd->query("UPDATE Bateau SET nomBateau = '$nom', prix = '$prix' WHERE idBateau = $idBateau");
    }

    if(isset($_POST['delete']))
    {
        $requ = $bdd->query("DELETE FROM Bateau WHERE idBateau =".$_POST['suppBateau']);
    }
?>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

    <div class="row mt-3 mb-3">
        <div class="col-sm-6">
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
        </div>
      </div>

        <div class="col-sm-6">
        <h2>Bateau non ajouter</h2>
        <div class="table-responsive">
            <table class="table table-striped" id="table1">
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
                    $rows = $bdd->query("SELECT * FROM Bateau");
                    foreach($rows as $row):
                    
                        $ros = $bdd->query("SELECT * FROM Boutique WHERE id_bateau =".$row->idBateau);
                        foreach($ros as $ro):
                        
                            if($ro->active === 'non-confirme')
                            {
                ?>
                    <tr>
                        <td><?= $row->idBateau?></td>
                        <td><?= $row->nomBateau?></td>
                        <td><?= $row->prix?> €</td>
                        <td><?= $ro->active ?></td>
                        <td><button class="btn btn-success" data-toggle="collapse" data-target="#addBoutique<?= $row->idBateau?>">Modifier</button></td>
                        <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete<?= $row->idBateau?>">Supprimer</button></td>
                    </tr>
                    <tr id="addBoutique<?= $row->idBateau?>" class="collapse">
                        <form method="POST">
                            <td><?= $row->idBateau?></td>
                            <td>
                                <div class="form-group">
                                    <input type="text" class="form-control" value="<?= $row->nomBateau ?>" name="nom">
                                </div>   
                            </td>
                            <td>
                                <div class="form-group">
                                    <input type="text" class="form-control" value="<?= $row->prix ?>" name="prix">
                                </div>   
                            </td>
                            <td>
                                <select class="form-control" name="status">
                                    <option value="non-confirme">non-confirme</option>
                                    <option value="confirme">Confirme</option>
                                </select>
                            </td>
                            <td></td>
                            <td>
                                <div class="form-group">
                                    <input type="hidden"  name="idBoutique" value="<?= $ro->idBoutique ?>"/>
                                    <input type="hidden"  name="idBateau" value="<?= $row->idBateau ?>"/>
                                    <button type="submit" class="btn btn-warning" style="color: white;" name="update">Modifier</button>
                                </div>   
                            </td>
                        </form>
                    </tr>

                    <!-- Modal -->
                    <div class="modal fade" id="delete<?= $row->idBateau ?>" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">Supprimer le bateau <?= $row->nomBateau?></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    Etes vous sur de vouloir supprimer le bateau <?= $row->nomBateau?> ?
                                </div>
                                <div class="modal-footer">
                                    <form method="POST">
                                        <input type="hidden" value="<?= $row->idBateau?>" name="suppBateau"/>
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
        </div>
    </div>
</div>

    <h2>All Boats</h2>
    <div class="table-responsive">
        <table class="table table-striped table-bordered" id="table2">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
            <?php
                $rs = $bdd->query("SELECT * FROM Bateau");
                
                foreach($rs as $r):
            ?>
                <tr>
                    <td><?= $r->idBateau ?></td>
                    <td><?= $r->nomBateau ?></td>
                    <td><?= substr($r->description, -20)?> ...</td>
                    <td><?= number_format($r->prix,2,',',' ')?> €</td>
                    <td>
                    <?php 
                        $res = $bdd->query("SELECT * FROM Boutique WHERE id_bateau =".$r->idBateau);

                        foreach($res as $re):
                            echo $re->active;
                        endforeach;
                    ?>
                    </td>
                </tr>
            <?php 
                endforeach;
            ?>
            </tbody>
        </table>
    </div>
</main>
<?php
}else{
    header('Location: index');
}
?>