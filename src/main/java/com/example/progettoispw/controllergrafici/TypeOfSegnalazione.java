package com.example.progettoispw.controllergrafici;

public enum TypeOfSegnalazione {

    //viene utilizzato dal controller grafico delle attive e risolte, che lo passano al bean lista elementi
    //il quale lo fornisce al controller applicativo, che in base al tipo che c'e' nel bean crea il giusto dao che
    //effettuerà o le  interrogazioni su segnalazioni attive o le interrogazioni su segnalazioni risolte
    ATTIVE,
    RISOLTE
}
