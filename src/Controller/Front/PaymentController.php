<?php

namespace App\Controller\Front;

use App\Entity\Abonnement;
use App\Entity\Commande;
use App\Repository\AbonnementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Stripe\Stripe;
use Stripe\Checkout\Session;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class PaymentController extends AbstractController
{
    private $basket;
    private $session;
    public function __construct()
    {
        $this->basket = new Commande();
        // TODO: Load the config in a cleaner way
        $this->session = new Session();
    }
    /**
     * @Route("/front/payment", name="front_payment")
     */
    public function index(): Response
    {
        return $this->render('front/payment/index.html.twig', [
            'controller_name' => 'PaymentController',
        ]);
    }

    /**
     * @Route("/front/checkout/{id}", name="checkout")
     */

    public function checkout($stripeSK,$id,AbonnementRepository $abonnements): Response
    {
        $abonnement= $abonnements->find($id);
        $prix=$abonnement->getCout();

       Stripe::setApiKey($stripeSK);
        $session =Session::create([
          'payment_method_types'=>['card'],
            'line_items'=>[[
                'price_data'=>[
                    'currency'=>'usd',
                    'product_data'=>[
                        'name'=>'Abonnement',
                    ],
                    'unit_amount'=>$prix,
                ],
                'quantity'=>1,
            ]],
            'mode'=>'payment',
            'success_url'=>$this->generateUrl('success_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
            'cancel_url'=>$this->generateUrl('cancel_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
        ]);
        return $this->redirect($session->url, 303);


    }
    /**
     * @Route("/success_url", name="success_url")
     */
    public function successUrl(): Response
    {
        return $this->render('front/payment/success.html.twig', [
            'controller_name' => 'PaymentController',
        ]);
    }
    /**
     * @Route("/cancel_url", name="cancel_url")
     */
    public function cancelUrl(): Response
    {
        return $this->render('front/payment/cancel.html.twig', [
            'controller_name' => 'PaymentController',
        ]);
    }

}
