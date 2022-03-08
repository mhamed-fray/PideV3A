<?php

namespace App\Controller\Front\Testmaker;


use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Repository\TestRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use App\Form\TestType;
use App\Entity\Test;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Doctrine\ORM\EntityManagerInterface;





use Dompdf\Dompdf;
use Dompdf\Options;

use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Ob\HighchartsBundle\Highcharts\Highchart;
use App\Data\SearchData;
use App\Form\SearchForm;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\BarChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\LineChart;
use Symfony\Component\Form\Extension\Core\Type\SearchType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;

class testController extends AbstractController
{
    /**
     * @Route("/front/testmaker/test", name="front_testmaker_test")
     */
    public function index(): Response
    {
        return $this->render('front/testmaker/test/index.html.twig', [
            'controller_name' => 'testController',
        ]);
    }


    /**
     * @param TestRepository $repository
     * @Route("/front/testmaker/alltests", name="all_tests")
     */
    public function liste(TestRepository $repository,Request $request, PaginatorInterface $paginator): Response
    {

        $tests = $repository->Sujetall();
        $tests = $paginator->paginate(
        // Doctrine Query, not results
            $tests,
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            2);


        return $this->render('front\testmaker\test\alltests.html.twig', [
            'alltests' => $tests,
        ]);

    }

    /**
     * @Route("front/testmaker/test/edittest/{id}", name="edit_test")
     */
    public function edit(TestRepository $repository,$id, Request $request ):response
    {
        $Test=$repository->find($id);

        $form=$this->createForm(TestType::class, $Test);

        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            $this->addFlash(
                'info',
                'edit successfly'
            );
            return $this->redirectToRoute('all_tests');
        }
        return $this->render('front\testmaker\test\edittest.html.twig',[

            'form' =>$form->createView()
        ]);
    }

    /**
     * @Route("front/testmaker/test/addtest", name="add_test")
     */
    public function createAction(Request $request) {

        $Test = new test();

        $form = $this->createForm(TestType::class,$Test);

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($Test);
            $em->flush();
                    $this->addFlash(
                        'info',
                        'added successfly'
                    );
            return $this->redirectToRoute('all_tests');

        }

        return $this->render('front\testmaker\test\addtest.html.twig', ['form' => $form->createView()]);

    }

    /**
     * @Route("front/testmaker/test/deletetest/{id}", name="delete_test")
     */
    public function deleteClass($id)
    {

        $em = $this->getDoctrine()->getManager();
        $Test = $this->getDoctrine()->getRepository(Test::class)->find($id);
        $em->remove($Test);
        $em->flush();
        $this->addFlash(
            'info',
            'supprimer successfly'
        );
        return $this->redirectToRoute("all_tests");

    }

    /**
     * @Route("front/testmaker/test/rienderoute", name="next_test")
     */
    public function rienderoute()
    {
        return $this->render('front\testmaker\test\next.html.twig');

    }


    /**
     * @Route("front/testmaker/test/addsujet", name="add_sujet")
     */
    public function addsujet(Request $request) {

        $Sujet = new sujet();

        $form = $this->createForm(SujetType::class,$Sujet);
        $form->add('suivant',SubmitType::class);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($Sujet);
            $em->flush();

            return $this->redirectToRoute('all_tests');
        }
        return $this->render('front\testmaker\test\next.html.twig', ['form' => $form->createView()]);

    }
    /**
     * @param TestRepository $repository
     * @Route("/back/testmaker/alltests", name="test1")
     */
    public function liste1(TestRepository $repository): Response
    {

        $tests = $repository->Sujetall();

        return $this->render('back\tests.html.twig', [
            'alltests' => $tests,
        ]);
    }
    /**
     * @Route("/search/", name="serie-search")
     */
    public function searchSeries(TestRepository $testrepository, Request $request)
    {
        $tests = $testrepository->findByNamePopular(
            $request->query->get('query')
        );

        $entityManager = $this->getDoctrine()->getManager();
        $categoryRepository=$entityManager->getRepository(Test::class);
        $categories=$categoryRepository->findAll();

        return $this->render('back\tests.html.twig', [
            'controller_name' => 'testController',
            'alltests'=>$tests,

        ]);}



}

