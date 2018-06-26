//
//  GameScene.swift
//  wheelchair_v2
//
//  Created by Daniel Münstermann on 21.06.18.
//  Copyright © 2018 Daniel Münstermann. All rights reserved.
//

import SpriteKit
import GameplayKit

class GameScene: SKScene {
    
    let joystick = Joystick()
    let player = SKSpriteNode(color: UIColor.red, size: CGSize(width: 50, height: 50))
    
    override func didMove(to view: SKView) {
        
        joystick.position = CGPoint(x:0, y:0)
        addChild(joystick)
        addChild(player)
        
        player.physicsBody = SKPhysicsBody(circleOfRadius: 10)
        player.position = CGPoint(x: 300, y: 300)
        
        self.physicsBody = SKPhysicsBody(edgeLoopFrom: frame)
        self.physicsWorld.gravity = CGVector(dx: 0, dy: 0)
        
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?){
        joystick.moveJoystick(touch: touches.first!)
        
        joystick.joystickAction = {(x: CGFloat, y: CGFloat) in
            self.player.physicsBody?.applyForce(CGVector(dx: x * 70, dy: y * 70))
        }
        
        
    }
}
