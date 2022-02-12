<?php

namespace App\Entity;

use App\Repository\ChoixRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ChoixRepository::class)
 */
class Choix
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="boolean")
     */
    private $etat_choix;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEtatChoix(): ?bool
    {
        return $this->etat_choix;
    }

    public function setEtatChoix(bool $etat_choix): self
    {
        $this->etat_choix = $etat_choix;

        return $this;
    }
}
