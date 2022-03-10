<?php

namespace App\Entity;

use App\Repository\LikesRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=LikesRepository::class)
 */
class Likes
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="likes_id")
     */
    private $user_id;

    /**
     * @ORM\ManyToOne(targetEntity=Abonnement::class, inversedBy="likes_id")
     */
    private $abonnement_id;

    /**
     * @ORM\Column(type="integer")
     */
    private $aimer;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUserId(): ?User
    {
        return $this->user_id;
    }

    public function setUserId(?User $user_id): self
    {
        $this->user_id = $user_id;

        return $this;
    }

    public function getAbonnementId(): ?Abonnement
    {
        return $this->abonnement_id;
    }

    public function setAbonnementId(?Abonnement $abonnement_id): self
    {
        $this->abonnement_id = $abonnement_id;

        return $this;
    }

    public function getAimer(): ?int
    {
        return $this->aimer;
    }

    public function setAimer(int $aimer): self
    {
        $this->aimer = $aimer;

        return $this;
    }
}
