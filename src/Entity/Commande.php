<?php

namespace App\Entity;

use App\Repository\CommandeRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CommandeRepository::class)
 */
class Commande
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="commandes")
     * @ORM\JoinColumn(nullable=false)
     */
    private $user;

    /**
     * @ORM\ManyToOne(targetEntity=Abonnement::class, inversedBy="commandes")
     * @ORM\JoinColumn(nullable=false)
     */
    private $abonnement;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $stripe_token;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $brand_token;

    /**
     * @ORM\Column(type="integer")
     */
    private $last4_stripe;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $id_change_stripe;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $status_stripe;

    /**
     * @ORM\Column(type="integer")
     */
    private $Prix;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }

    public function getAbonnement(): ?Abonnement
    {
        return $this->abonnement;
    }

    public function setAbonnement(?Abonnement $abonnement): self
    {
        $this->abonnement = $abonnement;

        return $this;
    }

    public function getStripeToken(): ?string
    {
        return $this->stripe_token;
    }

    public function setStripeToken(string $stripe_token): self
    {
        $this->stripe_token = $stripe_token;

        return $this;
    }

    public function getBrandToken(): ?string
    {
        return $this->brand_token;
    }

    public function setBrandToken(string $brand_token): self
    {
        $this->brand_token = $brand_token;

        return $this;
    }

    public function getLast4Stripe(): ?int
    {
        return $this->last4_stripe;
    }

    public function setLast4Stripe(int $last4_stripe): self
    {
        $this->last4_stripe = $last4_stripe;

        return $this;
    }

    public function getIdChangeStripe(): ?string
    {
        return $this->id_change_stripe;
    }

    public function setIdChangeStripe(string $id_change_stripe): self
    {
        $this->id_change_stripe = $id_change_stripe;

        return $this;
    }

    public function getStatusStripe(): ?string
    {
        return $this->status_stripe;
    }

    public function setStatusStripe(string $status_stripe): self
    {
        $this->status_stripe = $status_stripe;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->Prix;
    }

    public function setPrix(int $Prix): self
    {
        $this->Prix = $Prix;

        return $this;
    }
}
