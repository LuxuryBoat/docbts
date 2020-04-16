<?php 
if(isset($_SESSION['admin'])){
    if(isset($_POST['submit']))
    {
        $url = $_POST['url'];
        $nom = strtoupper($_POST['nom']);
        $description = $_POST['description'];
        $prix = $_POST['prix'];
        $session = $_SESSION['admin'];
        $categorie = $_POST['categorie'];
        $dateTime = date('Y-m-d H:i:s');
    
        $requete = $bdd->query("INSERT INTO Bateau(imageBateau, nomBateau, description, prix, idEmployer, idCategorie, created_at) VALUES('$url','$nom','$description','$prix','$session','$categorie','$dateTime')");
    }
    
    ?>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Ajouter un bateau</h1>
    </div>
    
    <form action="upload.php" enctype="multipart/form-data" method="post">
        
        <div class="col-sm-6 p-1">
                <input type="file" class="form-control-file border" name="file" />
                <input name="submit" type="submit" value="Upload File" />
                <!--<input type="text" class="form-control" id="url" placeholder="url image" name="url" />-->
        </div>

    </form>

      <form method="POST">
        <div class="form-row">
            
            <!--
            <div class="col-sm-6 p-1">
               <input type="file" class="form-control-file border" name="file" />
               <input type="text" class="form-control" id="url" placeholder="url image" name="url" />
            </div>
            -->
            
            <div class="col-sm-6 p-1">
                <input type="text" class="form-control" placeholder="url image" id="url" name="url" />
                    <select name="thelist" onChange="combo(this, 'url')" onMouseOut="comboInit(this, 'url')" >
                        <option>/assets/pictures/boat/</option>
                        <option>/assets/pictures/caroussel/</option>
                        <option>/assets/pictures/logo/</option>
                    </select> 
                    
                    <script>                                      
                        function combo(thelist, url)
                        {
                            url = document.getElementById(url);  
                            var idx = thelist.selectedIndex;
                            var content = thelist.options[idx].innerHTML;
                            url.value = content;	
                        }
                    </script>
            </div>
            

            <div class="col-sm-6 p-1">
                <input type="text" class="form-control" id="nom" placeholder="Nom" name="nom">
            </div>

        </div>
        <div class="form-row">
            <div class="col-sm-6 p-1">
                <input type="number" class="form-control" id="prix" placeholder="Prix" name="prix">
            </div>
            <div class="col-sm-6 p-1">
                <select class="form-control" name="categorie" id="sel1">
                    <?php
                        $categories = $bdd->query("SELECT * FROM Categorie");
                        foreach($categories as $categorie):
                    ?>
                        <option value="<?= $categorie->idCategorie ?>"><?= $categorie->idCategorie ?>- <?= $categorie->nomCategorie ?></option>
                        <?php endforeach; ?>
                </select>
            </div> 
        </div>
        <div class="form-group">
            <textarea class="form-control" rows="5" id="comment" name="description"></textarea>
        </div>
        <button type="submit" name="submit" class="btn btn-success">Ajouter</button>
      </form>
</main>

<?php 
}
?>