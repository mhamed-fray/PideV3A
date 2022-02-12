<?php

namespace App\Entity;

use App\Repository\CategorieRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CategorieRepository::class)
 */
class Categorie
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $type_categorie;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTypeCategorie(): ?string
    {
        return $this->type_categorie;
    }

    public function setTypeCategorie(string $type_categorie): self
    {
        $this->type_categorie = $type_categorie;

        return $this;
    }
}
