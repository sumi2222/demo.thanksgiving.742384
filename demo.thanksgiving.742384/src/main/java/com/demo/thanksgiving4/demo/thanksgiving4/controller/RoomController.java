package com.demo.thanksgiving4.demo.thanksgiving4.controller;

import com.demo.thanksgiving4.demo.thanksgiving4.entity.Item;
import com.demo.thanksgiving4.demo.thanksgiving4.entity.Room;
import com.demo.thanksgiving4.demo.thanksgiving4.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/room")
    public class RoomController {

        private static Logger LOGGER = LoggerFactory.getLogger(com.demo.thanksgiving4.demo.thanksgiving4.controller.RoomController.class);
        public final RoomRepository roomRepository;

        public RoomController(RoomRepository roomRepository) {
            this.roomRepository = roomRepository;
        }

        @GetMapping("/getAvailableRooms")
        public Iterable<Room> getAllItems() {
            return this.roomRepository.findAll();
        }

        @GetMapping("/getRoomById/{roomId}")
        public Room getAllItems(@PathVariable("roomId") Long roomId) {
            LOGGER.info("Is itemId available ? : " + this.roomRepository.existsById(roomId));
            return this.roomRepository.findById(roomId).get();
        }
        @PostMapping("/createRoom")
        public ResponseEntity<Room> createRoom(@RequestBody Room room) {
            if ((room.getRoomId() != null) && (this.roomRepository.existsById(room.getRoomId()))){
                return  ResponseEntity.status(HttpStatus.OK).body(this.roomRepository.save(room));
            }
            LOGGER.info("====================ITEM is  =====================================: " +room.toString());
            return ResponseEntity.status(HttpStatus.OK).body(this.roomRepository.save(room));
        }

        @PutMapping("/updateRoom")
        public Room updateRoom(@RequestBody Room room){ return this.roomRepository.save(room); }


        @DeleteMapping("/deleteRoom/{roomId}")
        public void deleteRoom(@PathVariable("roomId") Long roomId){
            this.roomRepository.deleteById(roomId);
        }

}
