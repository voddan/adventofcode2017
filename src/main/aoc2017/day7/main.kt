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

    val childrenNames = if(groups.size > 4) groups[4].split(", ") else listOf()

    return Node(name, weight, childrenNames)
}

fun buildGraph(nodes: List<Node>): Node {
    val nodeMap = nodes.associateByTo(mutableMapOf()) { it.name }
    val parents = nodes.filter { it.childrenNames.isNotEmpty() }

    parents.forEach { node ->
        node.children = node.childrenNames
                .map { chName -> nodeMap.remove(chName) ?: throw RuntimeException("Unknown name $chName") }
                .toSet()

        node.children.forEach {it.parent = node}
    }

    val roots = nodeMap.values

    assert(roots.size == 1)

    return roots.first()
}

fun taskA(input: List<String>): String {
    return ""
}

fun main(args: Array<String>) {
}
