package aoc2017.day7

import kotlin.properties.Delegates


data class Node(val name: String,
                val weight: Int,
                val childrenNames: List<String>
) {
    var parent by Delegates.observable<Node?>(null) {_, old, new ->
        assert(old == null)
        assert(new != null)
    }

    var children by Delegates.observable<Set<Node>>(emptySet()) { _, old, new ->
        assert(old == emptySet<Node>())
    }
}

fun parseNode(str: String): Node {
    val re = Regex("""(\w+) \((\d+)\)( -> (.+))?""")
    val groups = re.matchEntire(str)?.groupValues ?: throw RuntimeException("$str did not match :(")

    val name = groups[1]
    val weight = groups[2].toIntOrNull() ?: throw RuntimeException("${groups[2]} is not digitised :(")

    val childrenNames = if(groups[4].isNotBlank()) groups[4].split(", ") else listOf()

    assert(childrenNames.all { it.isNotBlank() })

    return Node(name, weight, childrenNames)
}

fun buildGraph(nodes: List<Node>): Node {
    val nodeMap = nodes.associateByTo(mutableMapOf()) { it.name }
    val parents = nodes.filter { it.childrenNames.isNotEmpty() }

    parents.forEach { node ->
        node.children = node.childrenNames
                .map { chName -> nodeMap.remove(chName) ?: throw RuntimeException("Unknown name $chName :(") }
                .toSet()

        node.children.forEach {it.parent = node}
    }

    val roots = nodeMap.values

    assert(roots.size == 1)

    return roots.first()
}

fun taskA(input: List<String>): String {
    val nodes = input.map { parseNode(it) }
    val root = buildGraph(nodes)
    return root.name
}


sealed class BalanceResult {
    data class AllGood(val weight: Int) : BalanceResult()
    data class NeedUpdate(val node: Node, val correctWeight: Int) : BalanceResult()
}

fun balanceGraph(node: Node): BalanceResult {
    return BalanceResult.AllGood(0)
}

fun taskB(input: List<String>): Int {
    val nodes = input.map { parseNode(it) }
    val root = buildGraph(nodes)
    val res = balanceGraph(root)
    return (res as BalanceResult.NeedUpdate).correctWeight
}

fun main(args: Array<String>) {
}
