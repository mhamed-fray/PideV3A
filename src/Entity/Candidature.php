<?php

namespace App\Entity;

use App\Repository\CandidatureRepository;
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
    private $etat_candidature;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $message;

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
}
