package com.udea.prestamos.model.dao;

import com.udea.prestamos.model.Devices;
import com.udea.prestamos.model.DevicesId;
import java.util.List;

/**
 *
 * @author Juan Diego
 */
public interface DispositivoDAO {

    public List<Devices> getDispositivos();

    public void updateDispositivo(Devices device);

    public Devices getDispositivo(DevicesId deviceId);

    public void registraDispositivo(Devices device);

    public List<Devices> getDispositivo(String code, String name, String type);
}
