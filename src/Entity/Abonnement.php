<?php

namespace App\Entity;

use App\Repository\AbonnementRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=AbonnementRepository::class)
 */
class Abonnement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     *      min = 2,
     *      max = 10,
     *      minMessage = "Le nom doit contenir au moins {{ limit }} caractères",
     *      maxMessage = "Le nom doit contenir au maximum {{ limit }} caractères"
     * )
     * @Assert\NotBlank(
     *     message="Ce champs ne doit pas etre vide")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     *      min = 10,
     *      max = 100,
     *      minMessage = "Votre description doit contenir au moins {{ limit }} caractères",
     *      maxMessage = "Votre description doit contenir au maximum {{ limit }} caractères"
     * )
     * @Assert\NotBlank(
     *     message="Ce champs ne doit pas etre vide")
     */
    private $description;

    /**
     * @ORM\Column(type="integer")
     * @Assert\GreaterThan(
     *     value = 0,
     *     message="Le cout d'un abonnement ne doit pas etre egal ou inferieur à 0"
     * )
     * @Assert\NotBlank(
     *     message="Ce champs ne doit pas etre vide")
     *
     */
    private $cout;

    /**
     * @ORM\ManyToOne(targetEntity=Type::class, inversedBy="abonnements")
     */
    private $Type;

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(?string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getCout(): ?int
    {
        return $this->cout;
    }

    public function setCout(?int $cout): self
    {
        $this->cout = $cout;

        return $this;
    }

    public function getid(): ?int
    {
        return $this->id;
    }

    public function setid(int $id): self
    {
        $this->id = $id;

        return $this;
    }

    public function getType(): ?Type
    {
        return $this->Type;
    }

    public function setType(?Type $Type): self
    {
        $this->Type = $Type;

        return $this;
    }
}
