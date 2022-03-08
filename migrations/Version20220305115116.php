<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220305115116 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE likes ADD user_id_id INT DEFAULT NULL, ADD abonnement_id_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT FK_49CA4E7D9D86650F FOREIGN KEY (user_id_id) REFERENCES `user` (id)');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT FK_49CA4E7DF483159E FOREIGN KEY (abonnement_id_id) REFERENCES abonnement (id)');
        $this->addSql('CREATE INDEX IDX_49CA4E7D9D86650F ON likes (user_id_id)');
        $this->addSql('CREATE INDEX IDX_49CA4E7DF483159E ON likes (abonnement_id_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY FK_49CA4E7D9D86650F');
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY FK_49CA4E7DF483159E');
        $this->addSql('DROP INDEX IDX_49CA4E7D9D86650F ON likes');
        $this->addSql('DROP INDEX IDX_49CA4E7DF483159E ON likes');
        $this->addSql('ALTER TABLE likes DROP user_id_id, DROP abonnement_id_id');
    }
}
