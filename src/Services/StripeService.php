<?php
namespace Stripe\Service;
use App\Entity\Abonnement;

class StripeService{
   private $privateKey;
   public function __construct(){
       $this->privateKey=$_ENV['STRIPE_SECRET_KEY_TEST'];
   }

    /**
     * @param Abonnement $abonnement
     * @return \Stripe\PaymentIntent
     * @throws \Stripe\Exception\ApiErrorException
     */
    public function paymentIntent(Abonnement $abonnement){
        \Stripe\Stripe::setApiKey($this->privateKey);
        return \Stripe\paymentIntent::create([
            'amount'=>$abonnement.getCout()*100,
            'payment_method_types'=>['card']
        ]);
}
    public function paiement(
        $amount,
        $description,
        array $stripeParameter
    )
    {
        \Stripe\Stripe::setApiKey($this->privateKey);
        $payment_intent=null;
        if(isset($stripeParameter['stripeIntentId'])){
            $payment_intent=\Stripe\PaymentIntent::retrieve($stripeParameter['stripeIntentId']);
        }
        if($stripeParameter['stripeIntentId'] === 'succeeded'){

        }else{
            $payment_intent->cancel();
        }
        return $payment_intent;
    }
    public function stripe(array $stripeParameter,Abonnement $abonnement){
        return $this->paiement(
            $abonnement->getCout()*100,
            $abonnement->getNom(),
            $stripeParameter
        );
    }


}
