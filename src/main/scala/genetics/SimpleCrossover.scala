// -*- mode: Scala;-*- 
// Filename:    SimpleCrossover.scala 
// Authors:     lgm                                                    
// Creation:    Sun Feb  1 22:57:09 2015 
// Copyright:   Not supplied 
// Description: 
// ------------------------------------------------------------------------

package com.biosimilarity.skein.genetics

import scala.collection.immutable.Stream

trait Reproduction {
  def crossOver[A,B](
    mother : Stream[( A, B )],
    father : Stream[( A, B )],
    i : Int, j : Int,
    swapAB : A => B, swapBA : B => A
  ) : List[Stream[( A, B )]] = {
    val ( kid1M, kid1F ) = mother.drop( i ).take( j - i ).unzip
    val ( kid2M, kid2F ) = father.drop( i ).take( j - i ).unzip
    List[Stream[( A, B )]]( 
      kid1M.zip( kid2F ), 
      kid2M.zip( kid1F ),
      kid1F.map( swapBA ).zip( kid2M.map( swapAB ) ),
      kid2F.map( swapBA ).zip( kid1M.map( swapAB ) )
    )
  }
}
