//
//  Joystick.swift
//  WheelChair
//
//  Created by Daniel Münstermann on 21.06.18.
//  Copyright © 2018 Daniel Münstermann. All rights reserved.
//

import Foundation
import SpriteKit

class Joystick: SKNode {
    
    var joystick = SKShapeNode()
    var stick = SKShapeNode()
    
    let maxRange: CGFloat = 200
    
    var xValue: CGFloat = 0
    var yValue: CGFloat = 0
    
    var joystickAction: ((_ x: CGFloat, _ y: CGFloat) -> ())?
    
    override init(){
        
        // Große Joystick
        let joystickRect = CGRect(x:0, y:0, width: 700, height: 700)
        let joystickPath = UIBezierPath(ovalIn: joystickRect)
        
        joystick = SKShapeNode(path: joystickPath.cgPath, centered: true)
        joystick.fillColor = UIColor.lightGray
        joystick.strokeColor = UIColor.clear
        
        // kleiner Joystick
        let stickRect = CGRect(x:0, y:0, width: 150, height: 150)
        let stickPath = UIBezierPath(ovalIn: stickRect)
        
        stick = SKShapeNode(path: stickPath.cgPath, centered: true)
        stick.fillColor = UIColor.blue
        stick.strokeColor = UIColor.white
        stick.lineWidth = 4
        
        super.init()
        
        addChild(joystick)
        addChild(stick)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func moveJoystick(touch: UITouch) {
        let p = touch.location(in: self)
        
        let x = p.x.clamped(-maxRange, maxRange)
        let y = p.y.clamped(-maxRange, maxRange)
        
        stick.position = CGPoint(x: x, y: y)
        xValue = x / maxRange
        yValue = y / maxRange
        
        if let joystickAction = joystickAction {
            joystickAction(xValue, yValue)
        }
    }
}

extension CGFloat{
    
    func clamped(_ v1: CGFloat,_ v2: CGFloat) -> CGFloat {
        let min = v1 < v2 ? v1 : v2
        let max = v1 > v2 ? v1 : v2
        
        return self < min ? min : (self > max ? max : self)
    }
}







