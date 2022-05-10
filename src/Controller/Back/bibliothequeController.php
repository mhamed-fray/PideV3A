<?php

namespace App\Controller\Back;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\BibliothequeRepository;
use App\Entity\Question;
use App\Entity\Bibliotheque;


class bibliothequeController extends AbstractController
{
    /**
     * @Route("/back/bibliotheque", name="back_bibliotheque")
     */
    public function index(): Response
    {
        return $this->render('back/bibliotheque/index.html.twig', [
            'controller_name' => 'bibliothequeController',
        ]);
    }


    /**
     * @param BiliothequeRepository $repository
     * @Route("/back/biblio/allbiblios", name="back_all_biblio")
     */
    public function list_biblio(BibliothequeRepository $repository): Response
    {

        $biblios = $repository->findAll();
        
    
        return $this->render('Back\bibliotheque.html.twig', [
            'allbiblio' => $biblios
        ]);
    }


     /**
    * @Route("back/biblio/deletebiblio/{id}", name="back_delete_biblio")
    */
    public function delete_biblio($id)
    {

        $em = $this->getDoctrine()->getManager();
        $question = $this->getDoctrine()->getRepository(Question::class)->find($id);
        $em->remove($question);
        $em->flush();
        return $this->redirectToRoute("back_all_biblio");

    }


}
