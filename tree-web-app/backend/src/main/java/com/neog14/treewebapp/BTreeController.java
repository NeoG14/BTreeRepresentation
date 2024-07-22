package com.neog14.treewebapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/btree")
public class BTreeController {
    private BTree bTree;

    @PostMapping("/create")
    public ResponseEntity<BTree> createTree(@RequestParam int order) {
        this.bTree = new BTree(order);
        return ResponseEntity.ok(bTree);
    }

    @PostMapping
    public ResponseEntity<BTree> insertKey(@RequestParam int key) {
        this.bTree.insert(key);
        return ResponseEntity.ok(bTree);
    }
}
