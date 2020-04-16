<?php 
    session_start();
    setlocale(LC_TIME, "fr_FR");

    //import class
    require_once 'class/_header.php';

    //Verify Page existes
    if(!isset($_GET['page'])) {
        $page = "accueil";
    } else if(file_exists('templates/users/'.$_GET['page'].'.php')) {
        $page = $_GET['page'];
    } else if(file_exists('templates/admin/'.$_GET['page'].'.php')) {
        $page = $_GET['page'];
    } else if(file_exists('templates/'.$_GET['page'].'.php')) {
        $page = $_GET['page'];
    } else {
        $page = include('templates/errors/404.php');
    }

    ob_start();
        if (file_exists('templates/'.$page.'.php')) {
            require_once 'templates/'.$page.'.php';
            $pagesContent = ob_get_contents();
        } elseif (file_exists('templates/admin/'.$page.'.php')) {
            require_once 'templates/admin/'.$page.'.php';
            $pagesContent = ob_get_contents();
        }
        elseif (file_exists('templates/users/'.$page.'.php')) {
            require_once 'templates/users/'.$page.'.php';
            $pagesContent = ob_get_contents();
        }
	ob_end_clean();

	require 'elements/layout.php';