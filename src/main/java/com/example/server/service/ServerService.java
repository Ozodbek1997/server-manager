package com.example.server.service;

import com.example.server.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

public interface ServerService {

    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Boolean delete(Long id);
    Server get(Long id);
    Server update(Server server);


}
