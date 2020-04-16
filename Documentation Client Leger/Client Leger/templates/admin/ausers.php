<?php 
if(isset($_SESSION['admin'])){

    if(isset($_POST['delete']))
    {
        $requete = $bdd->query("DELETE FROM Compte WHERE IdCompte =".$_POST['suppCompte']);
    }

    if(isset($_POST['ban']))
    {
        $id = $_POST['id'];
        $val = $_POST['val'];
        $requete = $bdd->query("UPDATE Compte SET ban = $val WHERE IdCompte = $id");
    }
?>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <h2 class="mt-5">Utilisateurs</h2>
    <div class="table-responsive">
        <table class="table table-striped" id="table1">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Téléphone</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <?php
                $rows = $bdd->query("SELECT * FROM clientall");
                foreach($rows as $row):
            ?>
                <tr>
                    <td><?= $row->id?></td>
                    <td><?= $row->nom?></td>
                    <td><?= $row->prenom?></td>
                    <td><?= $row->email?></td>
                    <td><?= $row->telephone?></td>
                    <td>
                        <?php if($row->ban == 0){ ?>
                            <form method="POST">
                                <input type="hidden" name="id" value="<?= $row->id ?>"/>
                                <input type="hidden" name="val" value="1"/>
                                <button type="input" class="btn btn-danger" name="ban">Bannir</button>
                            </form>
                        <?php } else if($row->ban == 1){ ?>
                            <form method="POST">
                                <input type="hidden" name="id" value="<?= $row->id ?>"/>
                                <input type="hidden" name="val" value="0"/>
                                <button type="input" class="btn btn-primary" name="ban">Debannir</button>
                            </form>
                        <?php } ?>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete<?= $row->id ?>">Supprimer</button>
                    </td>
                </tr>

                <!-- Modal -->
                <div class="modal fade" id="delete<?= $row->id ?>" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deleteModalLabel">Supprimer le Compte <?= $row->nom ?></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Etes vous sur de vouloir supprimer le compte de <?= $row->nom ?> <?= $row->prenom ?> ?
                            </div>
                            <div class="modal-footer">
                                <form method="POST">
                                    <input type="hidden" value="<?= $row->id ?>" name="suppCompte"/>
                                    <button type="input" class="btn btn-danger" name="delete">Supprimer</button>
                                </form>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                            </div>
                        </div>
                    </div>
                </div>

            <?php 
                endforeach;
            ?>
            </tbody>
        </table>
    </div>


    <h2 class="mt-5">Employer</h2>
    <div class="table-responsive">
        <table class="table table-striped" id="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Téléphone</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <?php
                $requete = $bdd->query("SELECT * FROM employerall");
                foreach($rows as $row):
            ?>
                <tr>
                    <td><?= $row->id?></td>
                    <td><?= $row->nom?></td>
                    <td><?= $row->prenom?></td>
                    <td><?= $row->email?></td>
                    <td><?= $row->telephone?></td>
                    <td>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete<?= $row->id ?>">Supprimer</button>
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