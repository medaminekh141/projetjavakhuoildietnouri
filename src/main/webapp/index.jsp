<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Système de gestion apicole pour la gestion des fermiers, fermes et ruches">
    <title>Gestion Apicole - Accueil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary-color: #FFA500;
            --primary-hover: #FF8C00;
        }

        body {
            font-family: 'Poppins', sans-serif;
            overflow-x: hidden;
        }

        .hero-section {
            background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)),
            url('https://images.unsplash.com/photo-1587049352851-8d4e89133924?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1200&q=80');
            background-size: cover;
            background-position: center;
            color: white;
            padding: 120px 0;
            margin-bottom: 50px;
        }

        .feature-card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            height: 100%;
            border: none;
            border-radius: 10px;
            overflow: hidden;
        }

        .feature-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 30px rgba(0,0,0,0.15);
        }

        .card-img-top {
            height: 200px;
            object-fit: cover;
        }

        .btn-custom {
            background-color: var(--primary-color);
            border: none;
            font-weight: 600;
            padding: 8px 20px;
            border-radius: 50px;
        }

        .btn-custom:hover {
            background-color: var(--primary-hover);
            transform: translateY(-2px);
        }

        footer {
            background-color: #1a1a1a;
        }

        .display-4 {
            font-weight: 700;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.5);
        }

        @media (max-width: 768px) {
            .hero-section {
                padding: 80px 0;
            }

            .display-4 {
                font-size: 2.5rem;
            }
        }
    </style>
</head>
<body>
<!-- Navigation (optionnelle) -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
            <span style="color: var(--primary-color);">🍯</span> Apiculture Manager
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/fermiers?action=list">Fermiers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/fermes?action=list">Fermes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ruches?action=list">Ruches</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Section Hero -->
<section class="hero-section text-center">
    <div class="container">
        <h1 class="display-4 fw-bold mb-4">Système de Gestion Apicole</h1>
        <p class="lead mb-5">Optimisez la gestion de votre exploitation apicole avec notre solution complète</p>
        <a href="#features" class="btn btn-custom btn-lg">Découvrir les fonctionnalités</a>
    </div>
</section>

<!-- Features Section -->
<section id="features" class="py-5">
    <div class="container">
        <h2 class="text-center mb-5">Nos Modules Principaux</h2>
        <div class="row g-4 justify-content-center">
            <!-- Fermiers Card -->
            <div class="col-lg-4 col-md-6">
                <div class="card feature-card h-100">
                    <img src="https://images.unsplash.com/photo-1587049352851-8d4e89133924?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=600&q=80"
                         class="card-img-top"
                         alt="Apiculteur en train de travailler"
                         loading="lazy">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">Gestion des Fermiers</h5>
                        <p class="card-text flex-grow-1">Gérez les apiculteurs, leurs informations personnelles, leurs formations et leurs activités.</p>
                        <a href="${pageContext.request.contextPath}/fermiers?action=list" class="btn btn-custom align-self-start">Accéder au module</a>
                    </div>
                </div>
            </div>

            <!-- Fermes Card -->
            <div class="col-lg-4 col-md-6">
                <div class="card feature-card h-100">
                    <img src="https://images.unsplash.com/photo-1587049352851-8d4e89133924?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=600&q=80"
                         class="card-img-top"
                         alt="Ferme apicole avec plusieurs ruches"
                         loading="lazy">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">Gestion des Fermes</h5>
                        <p class="card-text flex-grow-1">Administrez vos sites apicoles, leurs localisations, caractéristiques et capacités.</p>
                        <a href="${pageContext.request.contextPath}/fermes?action=list" class="btn btn-custom align-self-start">Accéder au module</a>
                    </div>
                </div>
            </div>

            <!-- Ruches Card -->
            <div class="col-lg-4 col-md-6">
                <div class="card feature-card h-100">
                    <img src="https://images.unsplash.com/photo-1587049352851-8d4e89133924?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=600&q=80"
                         class="card-img-top"
                         alt="Ruche d'abeilles en activité"
                         loading="lazy">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">Gestion des Ruches</h5>
                        <p class="card-text flex-grow-1">Suivez l'état, la production et la santé de chaque ruche en temps réel.</p>
                        <a href="${pageContext.request.contextPath}/ruches?action=list" class="btn btn-custom align-self-start">Accéder au module</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Call to Action -->
<section class="bg-light py-5">
    <div class="container text-center">
        <h2 class="mb-4">Prêt à optimiser votre exploitation apicole?</h2>
        <a href="${pageContext.request.contextPath}/fermiers?action=new" class="btn btn-custom btn-lg me-3">Commencer</a>
        <a href="#features" class="btn btn-outline-dark btn-lg">En savoir plus</a>
    </div>
</section>

<footer class="py-4">
    <div class="container text-center">
        <p class="mb-0">© 2025 Système de Gestion Apicole. Tous droits réservés.</p>
        <p class="mb-0">Khouildi Med Amine && Nouri Taha</p>
        <p class="mb-0 mt-2">
            <a href="#" class="text-white text-decoration-none me-3">Mentions légales</a>
            <a href="#" class="text-white text-decoration-none me-3">Contact</a>
            <a href="#" class="text-white text-decoration-none">Aide</a>
        </p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Smooth scrolling pour les ancres
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });
</script>
</body>
</html>