package com.fiuni.sd.issuetracker.service.contacto;

import org.springframework.stereotype.Service;

import com.fiuni.sd.issuetracker.domain.Contacto;
import com.fiuni.sd.issuetracker.dto.ContactoDTO;
import com.fiuni.sd.issuetracker.dto.ContactoResultDTO;
import com.fiuni.sd.issuetracker.service.base.IBaseService;
@Service
public interface IContactoService extends IBaseService<ContactoDTO, Contacto, ContactoResultDTO>{

}
