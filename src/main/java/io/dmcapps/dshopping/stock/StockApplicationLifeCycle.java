package io.dmcapps.dshopping.stock;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
class StockApplicationLifeCycle {

    private static final Logger LOGGER = Logger.getLogger(StockApplicationLifeCycle.class);

    void onStart(@Observes StartupEvent ev) {
       
        LOGGER.info(" .d8888b. 88888888888 .d88888b.   .d8888b.  888    d8P              d8888 8888888b. 8888888"); 
        LOGGER.info("d88P  Y88b    888    d88P\" \"Y88b d88P  Y88b 888   d8P              d88888 888   Y88b  888  "); 
        LOGGER.info("Y88b.         888    888     888 888    888 888  d8P              d88P888 888    888  888  "); 
        LOGGER.info(" \"Y888b.      888    888     888 888        888d88K              d88P 888 888   d88P  888  "); 
        LOGGER.info("    \"Y88b.    888    888     888 888        8888888b            d88P  888 8888888P\"   888  "); 
        LOGGER.info("      \"888    888    888     888 888    888 888  Y88b          d88P   888 888         888  "); 
        LOGGER.info("Y88b  d88P    888    Y88b. .d88P Y88b  d88P 888   Y88b        d8888888888 888         888  "); 
        LOGGER.info(" \"Y8888P\"     888     \"Y88888P\"   \"Y8888P\"  888    Y88b      d88P     888 888       8888888");
        LOGGER.info("                                                                         Powered by Quarkus");
           
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application STOCK is stopping...");
    }
}