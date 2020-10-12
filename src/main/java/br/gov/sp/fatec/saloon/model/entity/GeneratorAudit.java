package br.gov.sp.fatec.saloon.model.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import br.gov.sp.fatec.saloon.model.tool.Data;
import br.gov.sp.fatec.saloon.model.tool.UsuarioLogado;

@MappedSuperclass
public abstract class GeneratorAudit {
    
    Long    _inc_usua;    
    Date    _inc_data;
    
    public GeneratorAudit(){

        set_Inc_Usua( UsuarioLogado.getInstance().getId() );
        set_Inc_Data( Data.today() );

    }

    public Long get_Inc_Usua() {
        return _inc_usua;
    }

    public void set_Inc_Usua(Long _inc_usua) {
        this._inc_usua = _inc_usua;
    }

    public Date get_Inc_Data() {
        return _inc_data;
    }

    public void set_Inc_Data(Date _inc_data) {
        this._inc_data = _inc_data;
    }

}

