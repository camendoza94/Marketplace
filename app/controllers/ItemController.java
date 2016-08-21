package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.ItemEntity;
import models.ProductEntity;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

public class ItemController extends Controller {

    /**
     * Obtención de todos los items por generación de petición GET /items
     * @return Los items
     */
    public CompletionStage<Result> getItems() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return ItemEntity.FINDER.all();
                        }
                        , jdbcDispatcher)
                .thenApply(
                        itemEntities -> {
                            return ok(toJson(itemEntities));
                        }
                );
    }

    /**
     * Creación de un nuevo item según los parametros de la petición POST /items
     * @return el item agregado
     */
    public CompletionStage<Result> createItem(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nItem = request().body().asJson();
        ItemEntity item = Json.fromJson( nItem , ItemEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    item.save();
                    return item;
                }
        ).thenApply(
                itemEntity -> {
                    return ok(Json.toJson(itemEntity));
                }
        );
    }

    //TODO delete, update, get particular

}