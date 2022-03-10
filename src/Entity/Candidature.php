<?php

namespace App\Entity;

use App\Repository\CandidatureRepository;
use Symfony\Component\Validator\Constraints as Assert;

use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CandidatureRepository::class)
 */
class Candidature
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="float", nullable=true, nullable=true)
     */
    private $note_test;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    public $etat_candidature;

    /**

     * @ORM\Column(type="string", length=255)
     *  @Assert\Length(
     *      min = 20,
     *      max = 200,

     * )
     */

    private $message;

    /**
     * @ORM\ManyToOne(targetEntity=Offre::class, inversedBy="candidatures")
     * @ORM\JoinColumn(nullable=false)
     */
    private $Offre;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $image;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNoteTest(): ?float
    {
        return $this->note_test;
    }

    public function setNoteTest(?float $note_test): self
    {
        $this->note_test = $note_test;

        return $this;
    }

    public function getEtatCandidature(): ?string
    {
        return $this->etat_candidature;
    }

    public function setEtatCandidature(string $etat_candidature): self
    {
        $this->etat_candidature = $etat_candidature;

        return $this;
    }

    public function getMessage(): ?string
    {
        return $this->message;
    }

    public function setMessage(string $message): self
    {
        $this->message = $message;

        return $this;
    }

    public function getOffre(): ?Offre
    {
        return $this->Offre;
    }

    public function setOffre(?Offre $Offre): self
    {
        $this->Offre = $Offre;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }
}
