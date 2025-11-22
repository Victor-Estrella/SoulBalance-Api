package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.entity.EmailEntity;
import br.com.fiap.SoulBalance.enun.StatusEmail;
import br.com.fiap.SoulBalance.repository.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    // Removido JavaMailSender

    @Transactional
    public EmailEntity sendEmail(EmailEntity emailEntity) {
        emailEntity.setSendDateEmail(LocalDateTime.now());
        // Simula envio de e-mail: sempre marca como SENT
        emailEntity.setStatusEmail(StatusEmail.SENT);
        return emailRepository.save(emailEntity);
    }


    public List<EmailEntity> findAll() {
        return emailRepository.findAll();
    }

    public Optional<EmailEntity> findById(UUID id) {
        return emailRepository.findById(id);
    }

}
