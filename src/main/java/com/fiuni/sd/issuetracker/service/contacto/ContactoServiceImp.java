package com.fiuni.sd.issuetracker.service.contacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.issuetracker.dao.IContactosDao;
import com.fiuni.sd.issuetracker.domain.Contacto;
import com.fiuni.sd.issuetracker.dto.ContactoDTO;
import com.fiuni.sd.issuetracker.dto.ContactoResultDTO;
import com.fiuni.sd.issuetracker.service.base.BaseServiceImpl;



@Service
public class ContactoServiceImp  extends BaseServiceImpl<ContactoDTO, Contacto, ContactoResultDTO> implements IContactoService{

	
	@Autowired
	IContactosDao contacto;
	@Override
	public ContactoDTO save(ContactoDTO dto) {
		final Contacto c = convertDtoToDomain(dto);
		final Contacto co = contacto.save(c);
		return convertDomainToDto(co);
	}

	@Override
	public ContactoDTO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactoResultDTO getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ContactoDTO convertDomainToDto(Contacto bean) {
		ContactoDTO contacto = new ContactoDTO();
		contacto.setDescripcion(bean.getDescripcion());
		contacto.setEmail(bean.getEmail());
		contacto.setId(bean.getId());
		return contacto;
	}

	@Override
	protected Contacto convertDtoToDomain(ContactoDTO dto) {
		Contacto contacto = new Contacto();
		contacto.setDescripcion(dto.getDescripcion());
		contacto.setEmail(dto.getEmail());
		contacto.setId(dto.getId());
		return contacto;

	}

}
