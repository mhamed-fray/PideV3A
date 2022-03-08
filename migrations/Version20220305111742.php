<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220305111742 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE likes (id INT AUTO_INCREMENT NOT NULL, aimer INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE abonnement ADD likes_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE abonnement ADD CONSTRAINT FK_351268BB2F23775F FOREIGN KEY (likes_id) REFERENCES likes (id)');
        $this->addSql('CREATE INDEX IDX_351268BB2F23775F ON abonnement (likes_id)');
        $this->addSql('ALTER TABLE user ADD likes_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE user ADD CONSTRAINT FK_8D93D6492F23775F FOREIGN KEY (likes_id) REFERENCES likes (id)');
        $this->addSql('CREATE INDEX IDX_8D93D6492F23775F ON user (likes_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE abonnement DROP FOREIGN KEY FK_351268BB2F23775F');
        $this->addSql('ALTER TABLE `user` DROP FOREIGN KEY FK_8D93D6492F23775F');
        $this->addSql('DROP TABLE likes');
        $this->addSql('DROP INDEX IDX_351268BB2F23775F ON abonnement');
        $this->addSql('ALTER TABLE abonnement DROP likes_id');
        $this->addSql('DROP INDEX IDX_8D93D6492F23775F ON `user`');
        $this->addSql('ALTER TABLE `user` DROP likes_id');
    }
}
