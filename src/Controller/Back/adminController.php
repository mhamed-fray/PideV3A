<?php

namespace App\Controller\Back;
use  App\Controller\Front\Entreprise;
use App\Form\CandidatureType;
use App\Repository\CandidatureRepository;
use App\Repository\OffreRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Offre;
use App\Entity\Candidature;
class adminController extends AbstractController
{
    /**
     * @Route("/profiladmin", name="profil_admin")
     */
    public function index(): Response
    {
        return $this->render('back/admin/index.html.twig', [
            'controller_name' => 'adminController',
        ]);
    }


    /**
     * @param CandidatureRepository $repository
     * @Route("/back/tous_condidatures", name="tous_condidatures_back")
     */
    public function listec_back(CandidatureRepository $repository): Response
    {

        $candidature=$repository->findAll();
        return $this->render('back/candidatures.html.twig', [
            'candidature' => $candidature,
        ]);
    }
    /**
     * @Route("/back/deletec/{id}", name="delete_condidature_back")
     */
    public function deletecandidature($id)
    {

        $em = $this->getDoctrine()->getManager();
        $candidature = $this->getDoctrine()->getRepository(Candidature::class)->find($id);
        $em->remove($candidature);
        $em->flush();
        return $this->redirectToRoute("tous_condidatures_back");

    }

    /**
     * @param OffreRepository $repository
     * @Route("/front/tousofffres_back", name="tous_offres_back")
     */
    public function listeB(OffreRepository $repository): Response
    {

        $Offre=$repository->findAll();
        return $this->render('back/offres.html.twig', [
            'Offre' => $Offre,
        ]);
    }


    /**
     * @Route("/deleteB/{id}", name="supprimer_offre_back")
     */
    public function deleteClassB($id)
    {

        $em = $this->getDoctrine()->getManager();
        $Offre = $this->getDoctrine()->getRepository(Offre::class)->find($id);
        $em->remove($Offre);
        $em->flush();
        return $this->redirectToRoute("tous_offres_back");

    }
    /**
     * @Route("/triid", name="triid")
     */

    public function trier()
    {
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT a FROM App\Entity\Offre a
            ORDER BY a.salaire '
        );


        $rep = $query->getResult();
        return $this->render('back/offres.html.twig',
        array('Offre' => $rep));

    }



}
