<?php

namespace App\Entity;

use App\Repository\TestRepository;
use Doctrine\ORM\Mapping as ORM;


use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass=TestRepository::class)
 */
class Test
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     *  @Assert\NotBlank
     * @Assert\GreaterThan("today")
     * @Groups("post:read")
     */
    private $datedebut;


    /**
     * @ORM\Column(type="date", nullable=true)
     * @Assert\NotBlank
     * * @Assert\GreaterThan(propertyPath="datedebut")
     * @Groups("post:read")

     *


     */
    private $datefin;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("post:read")
     * * @Assert\Length(
     *      min = 4,
     *      max = 20,
     *      minMessage = "Your title  must be at least {{ limit }} characters long",
     *      maxMessage = "Your tilte cannot be longer than {{ limit }} characters"
     *
     * )
     * @Assert\NotBlank(message="le champ titre est obligatoire ")

     */
    private $titre;


    /**
     * @ORM\OneToOne(targetEntity=Sujet::class, cascade={"persist", "remove"})
     * @Assert\Valid

     */
    private $Sujet;
    public function getSujet(): ?Sujet
    {
        return $this->Sujet;
    }

    public function setSujet(?Sujet $Sujet): self
    {
        $this->Sujet = $Sujet;

        return $this;
    }






    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->datedebut;
    }

    public function setDateDebut(\DateTimeInterface $datedebut): self
    {
        $this->datedebut = $datedebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->datefin;
    }

    public function setDateFin(?\DateTimeInterface $datefin): self
    {
        $this->datefin = $datefin;

        return $this;
    }
    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

        return $this;
    }
}
