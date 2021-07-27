package com.nefu.myspringboot.service;

import com.nefu.myspringboot.entity.Machine;
import com.nefu.myspringboot.mapper.MachineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class MachineService {
    @Autowired
    private MachineMapper machineMapper;

    public Machine selectMachine(long id) {
        return machineMapper.selectById(id);
    }

    public void updateMachine(Machine machine) {
        machineMapper.updateById(machine);
    }

    public void addMachine(Machine machine) {
        machineMapper.insert(machine);
    }

    public void deleteMachine(Machine machine) {
        machineMapper.updateById(machine);
    }

    public List<Machine> machineList() {
        return machineMapper.listMachine();
    }
}
