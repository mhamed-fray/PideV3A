<?php

namespace App\Repository;

use App\Entity\Evenement;
use App\Entity\Reservation;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Evenement|null find($id, $lockMode = null, $lockVersion = null)
 * @method Evenement|null findOneBy(array $criteria, array $orderBy = null)
 * @method Evenement[]    findAll()
 * @method Evenement[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class EvenementRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Evenement::class);
    }
    public function remove(Evenement $customer)
{
    $this->manager->remove($customer);
    $this->manager->flush();
}



 

    

    public function nombreDePlace($value,$id)
    {
        return $this->createQueryBuilder('e')
        ->update()
        ->set('e.nb_participants',':value')
        ->where('e.eve_id = :id')
        ->setParameter('value', $value)
        ->setParameter('id', $id)
        ->getQuery()
       ->execute();
        

    }

    // /**
    //  * @return Evenement[] Returns an array of Evenement objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('e')
            ->andWhere('e.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('e.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Evenement
    {
        return $this->createQueryBuilder('e')
            ->andWhere('e.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function affichetrie()
    {
        return $this->createQueryBuilder('e')
        ->select('e')
        ->orderBy('e.dat','DESC')
        ->getQuery()
        ->getResult();
    }
}
