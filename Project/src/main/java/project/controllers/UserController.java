package project.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.exceptions.messages.DefaultMessage;
import project.models.RealStateAgentModel;
import project.models.UserModel;
import project.services.UserService;
import project.validators.UserModelValidator;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Api(value = "/users", description = "Manage users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelValidator userValidator;

    @ApiOperation(
            value = "Creates a new user",
            notes = "",
            response = UserModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created", response = UserModel.class),
            @ApiResponse(code = 400, message = "Constrains fails", response = DefaultMessage.class)
    })
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> create(@RequestBody UserModel user) {
        userValidator.validateForCreate(user);
        UserModel toReturn = userService.create(user);
        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Gets a user by id",
            notes = "",
            response = UserModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found", response = UserModel.class),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "User not found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserModel> read(@PathVariable Integer id) {
        UserModel toReturn = userService.read(id);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Updates a user by id",
            notes = "",
            response = UserModel.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated", response = UserModel.class),
            @ApiResponse(code = 400, message = "Constrains fails or invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "User not found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> update(@PathVariable Integer id, @RequestBody UserModel user) {
        userValidator.validateForUpdate(user);
        UserModel toReturn = userService.update(id, user);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deletes a user by id",
            notes = ""
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 400, message = "Invalid id", response = DefaultMessage.class),
            @ApiResponse(code = 404, message = "User not found", response = DefaultMessage.class)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Gets all users",
            notes = "",
            response = UserModel.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "User returned")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserModel>> getAll() {
        List<UserModel> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/real-state-agents", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RealStateAgentModel>> getRealStateAgents(@PathVariable Integer id) {
        List<RealStateAgentModel> realStateAgents = userService.getRealStateAgents(id);
        return new ResponseEntity<>(realStateAgents, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/real-state-agents/{realStateAgentId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<RealStateAgentModel>> addRealStateAgent(@PathVariable Integer userId, @PathVariable Integer realStateAgentId) {
        List<RealStateAgentModel> realStateAgents = userService.addRealStateAgents(userId, realStateAgentId);
        return new ResponseEntity<>(realStateAgents, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/real-state-agents/{realStateAgentId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<RealStateAgentModel>> deleteRealStateAgent(@PathVariable Integer userId, @PathVariable Integer realStateAgentId) {
        List<RealStateAgentModel> realStateAgents = userService.deleteRealSateAgent(userId, realStateAgentId);
        return new ResponseEntity<>(realStateAgents, HttpStatus.OK);
    }
}
